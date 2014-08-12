package com.meekan.api;

/**
 * Class representing API query result status
 * 
 * @author Antti Leppä
 */
public class ResultMeta {

	/**
	 * Constructor
	 * 
	 * @param code
	 *            code
	 * @param errorType
	 *            errorType
	 * @param errorDetail
	 *            error details
	 */
	public ResultMeta(Integer code, String errorType, String errorDetail) {
		this.code = code;
		this.errorType = errorType;
		this.errorDetail = errorDetail;
	}

	/**
	 * Returns code
	 * 
	 * @return code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @return error type
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * Returns error details
	 * 
	 * @return error details
	 */
	public String getErrorDetail() {
		return errorDetail;
	}

	private Integer code;
	private String errorType;
	private String errorDetail;
}