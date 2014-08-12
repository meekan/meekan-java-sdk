package com.meekan.api;

public class MeekanApiException extends Exception {

	private static final long serialVersionUID = -4648725044925625363L;

	public MeekanApiException(String message) {
		super(message);
	}

	public MeekanApiException(Throwable t) {
		super(t);
	}
}
