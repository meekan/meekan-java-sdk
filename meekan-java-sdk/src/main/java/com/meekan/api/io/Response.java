package com.meekan.api.io;

/**
 * Class that represents response from IOHandler
 * 
 */
public class Response {

	/**
	 * Constructor.
	 * 
	 * @param responseContent
	 *            response content
	 * @param responseCode
	 *            response code
	 * @param message
	 *            response message
	 */
	public Response(String responseContent, int responseCode, String message) {
		this.responseCode = responseCode;
		this.responseContent = responseContent;
		this.message = message;
	}

	/**
	 * Returns message
	 * 
	 * @return response message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Return code
	 * 
	 * @return response code
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * Returns content
	 * 
	 * @return response content
	 */
	public String getResponseContent() {
		return responseContent;
	}

	private String responseContent;
	private String message;
	private int responseCode;
}
