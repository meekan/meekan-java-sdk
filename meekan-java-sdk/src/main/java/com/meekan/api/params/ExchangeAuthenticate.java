package com.meekan.api.params;

import java.util.HashMap;
import java.util.Map;

/**
 * Use this class to authenticate to exchange
 * 
 * @author idog
 * 
 */
public class ExchangeAuthenticate implements Authenticate<String> {

	private static final long serialVersionUID = -7989075042873002403L;
	public static final String EXCHANGE = "exchange";
	private String username;
	private String email;
	private String password;
	private String url;

	/**
	 * 
	 * @param username
	 *            - user name in exchange (f.e bob)
	 * @param email
	 *            - email in exchange (f.e bob@example.onmicrosoft.com)
	 * @param password
	 *            - password for account in exchange (f.e example1234)
	 * @param url
	 *            - url to schema in exchange (f.e https://outlook.office365.com/EWS/Exchange.asmx)
	 */
	public ExchangeAuthenticate(String username, String email, String password, String url) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.url = url;
	}

	public ExchangeAuthenticate() {
		// empty ctor
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ExchangeAuthenticate))
			return false;
		ExchangeAuthenticate other = (ExchangeAuthenticate) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public Map<String, String> toParamsMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", getEmail());
		params.put("username", getUsername());
		params.put("password", getPassword());
		params.put("url", getUrl());
		return params;
	}

	@Override
	public String getProviderName() {
		return EXCHANGE;
	}

	@Override
	public String getIdentifier() {
		return getEmail();
	}
}
