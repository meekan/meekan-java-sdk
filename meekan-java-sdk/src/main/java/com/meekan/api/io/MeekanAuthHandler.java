package com.meekan.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.ResultMeta;
import com.meekan.api.params.Authenticate;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.params.GoogleAuthenticate;
import com.meekan.api.params.ICloudAuthenticate;
import com.meekan.api.params.ICloudNewAuthenticate;
import com.meekan.api.params.ICloudOldAuthenticate;
import com.meekan.api.params.ICloudServerEntity;
import com.meekan.api.params.MeekanSessionCookies;
import com.meekan.api.utils.HttpUtils;
import com.meekan.api.utils.Utils;

/**
 * Meekan implementation of {@link AuthHandler}
 * 
 * @author idog
 * 
 */
public class MeekanAuthHandler implements AuthHandler {

	private CookieManager cookieManager;
	private IOHandler ioHandler;
	private static final String VALIDATE_ICLOUD_URL = "https://setup.icloud.com/setup/ws/1/validate";;

	public MeekanAuthHandler(IOHandler ioHandler) {
		this.cookieManager = MeekanCookieManager.getInstance();
		this.ioHandler = ioHandler;
	}

	private <T> ApiRequestResponse authenticate(Authenticate<T> authenticate) throws MeekanApiException, URISyntaxException, IOException {
		Map<String, T> params = authenticate.toParamsMap();
		String jsonMapStr = Utils.getJSONObjectMapper().writeValueAsString(params);
		Map<String, Collection<String>> callParams = new HashMap<String, Collection<String>>();
		callParams.put("state", Collections.singleton(jsonMapStr));
		ApiRequestResponse authenticateResponse = HttpUtils.doApiRequest(ApiMethod.GET,
				String.format("social_login/%s/complete", authenticate.getProviderName()), callParams, ioHandler);

		return authenticateResponse;
	}

	private ICloudServerEntity validate() throws IOException {
		URL aUrl = new URL(VALIDATE_ICLOUD_URL);
		HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
		try {
			connection.setRequestMethod("GET");
			initializeICloudConnection(connection, false);
			Map<String, List<String>> requestProperties = connection.getRequestProperties();
			int code = connection.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				return parseICloudResponse(connection, requestProperties);
			} else {
				return null;
			}

		} finally {
			connection.disconnect();
		}

	}

	private ICloudServerEntity parseICloudResponse(HttpURLConnection connection, Map<String, List<String>> requestProperties) throws IOException {
		List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		InputStream inputStream = connection.getInputStream();
		if (headerFields.get("Content-Encoding") != null && "gzip".equals(headerFields.get("Content-Encoding").get(0))) {
			inputStream = new GZIPInputStream(inputStream);
		}
		String responseData = MeekanIOHandler.readStream(inputStream);
		JsonNode jsonResponse = Utils.parseStringToJsonNode(responseData);
		String endpoint = jsonResponse.get("webservices").get("calendar").get("url").textValue();
		String dsid = jsonResponse.get("dsInfo").get("dsid").textValue();
		Map<String, List<String>> properties = new HashMap<String, List<String>>(requestProperties);
		properties.remove("Accept-Encoding");
		properties.remove("Accept");
		return new ICloudServerEntity(endpoint, dsid, cookies, properties);

	}

	private ICloudServerEntity icloudAuth(ICloudOldAuthenticate icloudAuthenticate) throws MalformedURLException, IOException {
		String url = "https://setup.icloud.com/setup/ws/1/login";
		URL aUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
		try {
			connection.setRequestMethod("POST");
			initializeICloudConnection(connection, true);
			String paramsStr = String.format("{\"extended_login\":false,\"password\":\"%s\",\"apple_id\":\"%s\"}", icloudAuthenticate.getPassword(),
					icloudAuthenticate.getICloudAppleId());
			Map<String, List<String>> requestProperties = connection.getRequestProperties();
			byte[] paramsBytes = paramsStr.getBytes(MeekanApi.UTF_8);
			connection.setRequestProperty("Content-Length", Integer.toString(paramsBytes.length));
			connection.setDoOutput(true);
			connection.getOutputStream().write(paramsBytes);

			connection.connect();

			int code = connection.getResponseCode();
			String responseMessage = connection.getResponseMessage();
			if (code == HttpURLConnection.HTTP_OK) {
				return parseICloudResponse(connection, requestProperties);
			}
		} finally {
			connection.disconnect();
		}

		return null;
	}

	private void initializeICloudConnection(HttpURLConnection connection, boolean includeAccept) throws ProtocolException {
		connection.setDoInput(true);
		if (includeAccept) {
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, compress");
		}
		connection.setRequestProperty("Host", "setup.icloud.com");
		connection.setRequestProperty("Origin", "https://www.icloud.com");
		connection.setRequestProperty("Referer", "https://www.icloud.com/");
		connection.setRequestProperty("User-Agent", "Opera/9.52 (X11; Linux i686; U; en)");
		connection.setRequestProperty("Content-Type", "text/plain");
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.8,he;q=0.6,it;q=0.4");
	}

	/**
	 * @see com.meekan.api.io.AuthHandler#icloudAuthenticate(com.meekan.api.params.ICloudOldAuthenticate)
	 */
	@Override
	public ApiRequestResponse icloudAuthenticate(ICloudAuthenticate authenticate) {
		if (authenticate instanceof ICloudOldAuthenticate) {
			return oldIcloudAuth(authenticate);
		} else {
			return newIcloudAuth((ICloudNewAuthenticate) authenticate);

		}
	}

	private ApiRequestResponse newIcloudAuth(ICloudNewAuthenticate authenticate) {
		String url = "https://setup.icloud.com/setup/get_account_settings";
		HttpURLConnection connection = null;
		try {
			URL aUrl = new URL(url);
			connection = (HttpURLConnection) aUrl.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			connection.setRequestProperty("Host", "setup.icloud.com");
			connection.setRequestProperty("User-Agent", "Opera/9.52 (X11; Linux i686; U; en)");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.8,he;q=0.6,it;q=0.4");
			String encodeBase64 = new String(Base64.encodeBase64((authenticate.getIdentifier() + ":" + authenticate.getPassword()).getBytes()));
			connection.setRequestProperty("Authorization", "Basic " + encodeBase64);
			connection.setRequestProperty("Content-Length", "0");
			connection.setRequestProperty("Connection", "keep-alive");

			connection.connect();

			int code = connection.getResponseCode();
			String responseMessage = connection.getResponseMessage();

			if (code == HttpURLConnection.HTTP_OK) {
				Map<String, List<String>> headerFields = connection.getHeaderFields();
				InputStream inputStream = connection.getInputStream();
				if (headerFields.get("Content-Encoding") != null && "gzip".equals(headerFields.get("Content-Encoding").get(0))) {
					inputStream = new GZIPInputStream(inputStream);
				}
				String responseData = MeekanIOHandler.readStream(inputStream);
				Document loadXMLFromString = Utils.loadXMLFromString(responseData);
				String dsprsid = loadXMLFromString.getElementsByTagName("string").item(8).getTextContent();
				String mme = loadXMLFromString.getElementsByTagName("string").item(1).getTextContent();
				authenticate.setMmeAuthAndDsprsid(mme, dsprsid);

				return authenticate(authenticate);
			} else {
				return new ApiRequestResponse(new ResultMeta(500, "", responseMessage), null);
			}

		} catch (MeekanApiException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		} catch (URISyntaxException e) {
			return new ApiRequestResponse(new ResultMeta(400, "", "Malformed URL"), null);
		} catch (IOException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		} catch (Exception e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private ApiRequestResponse oldIcloudAuth(ICloudAuthenticate authenticate) {
		ICloudOldAuthenticate icloudOldAuthenticate = (ICloudOldAuthenticate) authenticate;
		ICloudServerEntity res = null;
		try {

			res = validate();
			if (res == null) {
				res = icloudAuth(icloudOldAuthenticate);
			}

			if (res == null) {
				res = validate();
			}
			if (res != null) {
				icloudOldAuthenticate.setiCloudServerEntity(res);
			}
			return authenticate(icloudOldAuthenticate);

		} catch (MalformedURLException ignored) {
			return new ApiRequestResponse(new ResultMeta(400, "", "Malformed URL"), null);
		} catch (URISyntaxException e) {
			return new ApiRequestResponse(new ResultMeta(400, "", "Malformed URL"), null);
		} catch (IOException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		} catch (MeekanApiException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		}
	}

	/**
	 * @see com.meekan.api.io.AuthHandler#exchangeAuthenticate(com.meekan.api.params.ExchangeAuthenticate)
	 */
	@Override
	public ApiRequestResponse exchangeAuthenticate(ExchangeAuthenticate authenticate) {
		try {
			return authenticate(authenticate);
		} catch (MeekanApiException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		} catch (URISyntaxException e) {
			return new ApiRequestResponse(new ResultMeta(400, "", "Malformed URL"), null);
		} catch (IOException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		}
	}

	/**
	 * @see com.meekan.api.io.AuthHandler#cookiesAuthenticate(com.meekan.api.params.MeekanSessionCookies)
	 */
	@Override
	public void cookiesAuthenticate(MeekanSessionCookies cookies) {
		cookieManager.getCookieStore().add(MeekanApi.API_URI, new HttpCookie("session", cookies.getSession()));
		cookieManager.getCookieStore().add(MeekanApi.API_URI, new HttpCookie("session_name", cookies.getSessionName()));
	}

	@Override
	public ApiRequestResponse googleAuthenticate(GoogleAuthenticate googleAuthenticate) {
		Map<String, Collection<String>> callParams = new HashMap<String, Collection<String>>();
		callParams.put("code", Collections.singleton(googleAuthenticate.getCode()));
		callParams.put("gv", Collections.singleton("mobile"));
		try {
			return HttpUtils.doApiRequest(ApiMethod.GET, "social_login/google_oauth2/complete", callParams, ioHandler);
		} catch (MeekanApiException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		}
	}

}
