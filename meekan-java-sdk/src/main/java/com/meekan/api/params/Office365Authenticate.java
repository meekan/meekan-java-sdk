package com.meekan.api.params;

import java.util.Map;

public class Office365Authenticate implements Authenticate<String> {

	private static final long serialVersionUID = -2266932591450585772L;
	private static final String PROVIDER = "office365_oauth2";
	private String email;
	private String code;

	public Office365Authenticate(String email, String code) {
		this.email = email;
		this.code = code;
	}

	public Office365Authenticate() {
		// empty ctor
	}

	@Override
	public Map<String, String> toParamsMap() {
		return null;
	}

	@Override
	public String getIdentifier() {
		return email;
	}

	@Override
	public String getProviderName() {
		return PROVIDER;
	}

	public String getEmail() {
		return email;
	}

	public String getCode() {
		return code;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
