package com.meekan.api.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.ResultMeta;
import com.meekan.api.io.ApiMethod;
import com.meekan.api.io.IOHandler;
import com.meekan.api.io.Response;

public class HttpUtils {

	public static String createParams(Map<String, Collection<String>> params) throws MeekanApiException {
		if (params != null) {
			try {
				StringBuilder postData = new StringBuilder();
				if (Utils.isNotEmpty(params)) {
					for (Entry<String, Collection<String>> paramEntry : params.entrySet()) {
						for (String paramValue : paramEntry.getValue()) {
							if (postData.length() != 0) {
								postData.append('&');
							}
							postData.append(URLEncoder.encode(paramEntry.getKey(), MeekanApi.UTF_8));
							postData.append('=');
							postData.append(URLEncoder.encode(paramValue, MeekanApi.UTF_8));
						}
					}
				}
				return postData.toString();
			} catch (UnsupportedEncodingException e) {
				throw new MeekanApiException(e);
			}
		}

		return null;
	}

	/**
	 * Handles normal API request response
	 * 
	 * @param response
	 *            raw response
	 * @return ApiRequestResponse
	 * @throws MeekanApiException
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @throws JSONException
	 *             when JSON parsing error occurs
	 */
	public static ApiRequestResponse handleApiResponse(Response response) throws MeekanApiException {
		JsonNode responseObject = null;
		String errorDetail = null;
		if (response.getResponseCode() == 200) {
			try {
				responseObject = Utils.getJSONObjectMapper().readTree(response.getResponseContent());
			} catch (JsonProcessingException e) {
				throw new MeekanApiException(e);
			} catch (IOException e) {
				throw new MeekanApiException(e);
			}

		} else {
			errorDetail = response.getMessage();
		}

		return new ApiRequestResponse(new ResultMeta(response.getResponseCode(), "", errorDetail), responseObject);
	}

	/**
	 * Builds request URL
	 * 
	 * @param method
	 * 
	 * @param path
	 *            API endpoint
	 * @param auth
	 *            whether add oAuthToken parameter or not
	 * @param params
	 *            request parameters. Parameters should be added in parameter name, parameter value pairs
	 * @return URL
	 * @throws MeekanApiException
	 *             when something unexpected happens
	 */
	public static String getApiRequestUrl(ApiMethod method, String path, Map<String, Collection<String>> params) throws MeekanApiException {
		StringBuilder urlBuilder = new StringBuilder(MeekanApi.API_URL);
		urlBuilder.append(path);
		if (ApiMethod.GET.equals(method)) {

			String paramsStr = createParams(params);
			if (Utils.isNotEmpty(paramsStr)) {
				urlBuilder.append('?');
				urlBuilder.append(paramsStr);
			}
		}

		return urlBuilder.toString();
	}

	/**
	 * API Request
	 * 
	 * @param method
	 *            method used in request
	 * @param path
	 *            API endpoint
	 * @param auth
	 *            whether request should send oAuthToken or not
	 * @param params
	 *            request parameters.
	 * @param ioHandler
	 * @return response
	 * @throws JSONException
	 *             when JSON parsing error occurs
	 * @throws MeekanApiException
	 *             when something unexpected happens
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static ApiRequestResponse doApiRequest(ApiMethod method, String path, Map<String, Collection<String>> params, IOHandler ioHandler)
			throws MeekanApiException {
		String url = getApiRequestUrl(method, path, params);
		Response response = ioHandler.fetchData(url, method, params);
		return handleApiResponse(response);
	}

}
