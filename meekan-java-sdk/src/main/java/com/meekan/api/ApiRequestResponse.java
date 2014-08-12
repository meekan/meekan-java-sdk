package com.meekan.api;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Class that holds API request response
 * 
 */
public class ApiRequestResponse {

	private JsonNode response;
	private ResultMeta meta;

	/**
	 * Constructor
	 * 
	 * @param meta
	 *            status information
	 * @param response
	 *            response JSON Object
	 * @throws JSONException
	 *             when JSON parsing error occurs
	 */
	public ApiRequestResponse(ResultMeta meta, JsonNode response) {
		this.meta = meta;
		this.response = response;
	}

	/**
	 * Returns response JSON Object
	 * 
	 * @return response JSON Object
	 */
	public JsonNode getResponse() {
		return response;
	}

	/**
	 * Returns status information
	 * 
	 * @return status information
	 */
	public ResultMeta getMeta() {
		return meta;
	}

}