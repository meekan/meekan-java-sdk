package com.meekan.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.utils.HttpUtils;
import com.meekan.api.utils.Utils;

public class MeekanIOHandler implements IOHandler {
	private static String UTF_8 = "UTF-8";
	public static final String AUTHENTICATION_CHALLENGE_ERROR = "Received authentication challenge is null";
	private int timeoutInMillis;
	private CookieManager cookieManager;
	private String apiKey;

	public MeekanIOHandler(String apiKey) throws URISyntaxException {
		this(apiKey, 0);
	}

	public MeekanIOHandler(String apiKey, int timeoutInMillis) {
		this.apiKey = apiKey;
		this.timeoutInMillis = timeoutInMillis;
		cookieManager = MeekanCookieManager.getInstance();
		CookieHandler.setDefault(cookieManager);
		// dontcommit - disable for meekan.com also?
		disableCertificateValidation();
	}

	public static void disableCertificateValidation() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Ignore differences between given hostname and certificate hostname
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
		}
	}

	@Override
	public Response fetchData(String url, ApiMethod method, Map<String, Collection<String>> params) {

		try {
			URL aUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
			try {
				connection.setConnectTimeout(timeoutInMillis);
				connection.setInstanceFollowRedirects(false);
				connection.setReadTimeout(timeoutInMillis);
				connection.setDoInput(true);
				connection.setRequestProperty("Authorization", "Meekan " + apiKey);

				List<HttpCookie> cookies = cookieManager.getCookieStore().get(MeekanApi.API_URI);
				if (Utils.isNotEmpty(cookies)) {
					connection.setRequestProperty("Cookie2", "$Version=1");
					for (HttpCookie httpCookie : cookies) {
						httpCookie.setVersion(1);
						connection.setRequestProperty("Cookie", httpCookie.toString());
						connection.setUseCaches(false);
					}
				}

				connection.setRequestMethod(method.name());
				if (method != ApiMethod.GET) {
					String paramsStr = HttpUtils.createParams(params);
					if (Utils.isNotEmpty(paramsStr)) {
						byte[] paramsBytes = paramsStr.getBytes(MeekanApi.UTF_8);
						connection.setRequestProperty("Content-Length", Integer.toString(paramsBytes.length));
						connection.setDoOutput(true);
						connection.getOutputStream().write(paramsBytes);
					}

				}

				connection.connect();

				int code = connection.getResponseCode();
				if (code == HttpURLConnection.HTTP_OK) {
					List<String> cookieList = connection.getHeaderFields().get("set-cookie");
					if (cookieList != null) {
						for (String cookieTemp : cookieList) {
							try {
								cookieManager.getCookieStore().add(connection.getURL().toURI(), HttpCookie.parse(cookieTemp).get(0));
							} catch (URISyntaxException e) {
							}
						}
					}
				}

				return buildResponse(connection);
			} finally {
				connection.disconnect();
			}
		} catch (MeekanApiException e) {
			return new Response("", 500, e.getMessage());
		} catch (IOException e) {
			return new Response("", 500, e.getMessage());
		}
	}

	/**
	 * Reads input stream and returns it's contents as String
	 * 
	 * @param inputStream
	 *            input stream to be readed
	 * @return Stream's content
	 * @throws IOException
	 */
	public static String readStream(InputStream inputStream) throws IOException {
		StringWriter responseWriter = new StringWriter();

		char[] buf = new char[1024];

		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, UTF_8);
		int l = inputStreamReader.read(buf);
		while (l > 0) {
			responseWriter.write(buf, 0, l);
			l = inputStreamReader.read(buf);
		}

		responseWriter.flush();
		responseWriter.close();
		return responseWriter.getBuffer().toString();
	}

	private static Response buildResponse(HttpURLConnection connection) throws IOException {
		int code = connection.getResponseCode();
		String responseMessage = connection.getResponseMessage();
		if (code == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = connection.getInputStream();
			return new Response(readStream(inputStream), code, responseMessage);

		} else {
			return new Response("", code, getMessageByCode(code));
		}
	}

	/**
	 * Returns message for code
	 * 
	 * @param code
	 *            code
	 * @return Message
	 */
	private static String getMessageByCode(int code) {
		switch (code) {
		case HttpURLConnection.HTTP_BAD_REQUEST:
			return "Bad Request";

		case HttpURLConnection.HTTP_UNAUTHORIZED:
			return "Unauthorized";

		case HttpURLConnection.HTTP_FORBIDDEN:
			return "Forbidden";

		case HttpURLConnection.HTTP_NOT_FOUND:
			return "Not Found";

		case HttpURLConnection.HTTP_BAD_METHOD:
			return "Method Not Allowed";

		case HttpURLConnection.HTTP_INTERNAL_ERROR:
			return "Internal Server Error";

		default:
			return "Unknown";
		}
	}

}