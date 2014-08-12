package com.meekan.api.params;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ICloudServerEntity {
	private String endpoint;
	private String dsId;
	private List<HttpCookie> cookies;
	private Map<String, String> headers;

	/**
	 * @param endpoint
	 *            - endpoint from icloud server
	 * @param dsId
	 *            - dsid from icloud server
	 * @param cookies
	 *            - cookies from icloud server
	 * @param requestProperties
	 *            - the headers we used to get the details
	 */
	public ICloudServerEntity(String endpoint, String dsId, List<HttpCookie> cookies, Map<String, List<String>> requestProperties) {
		this.endpoint = endpoint;
		this.dsId = dsId;
		this.cookies = cookies;
		setHeaders(requestProperties);
	}

	public String getEndpoint() {
		return endpoint;
	}

	public String getDsId() {
		return dsId;
	}

	public Object getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("dsid", getDsId());
		return params;
	}

	public List<HttpCookie> getCookies() {
		return cookies;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public void setCookies(List<HttpCookie> cookies) {
		this.cookies = cookies;
	}

	public Object getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = new HashMap<String, String>();
		for (String key : headers.keySet()) {
			this.headers.put(key, headers.get(key).get(0));
		}
		this.headers.put("Content-Type", "application/json");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cookies == null) ? 0 : cookies.hashCode());
		result = prime * result + ((dsId == null) ? 0 : dsId.hashCode());
		result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
		result = prime * result + ((headers == null) ? 0 : headers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ICloudServerEntity))
			return false;
		ICloudServerEntity other = (ICloudServerEntity) obj;
		if (cookies == null) {
			if (other.cookies != null)
				return false;
		} else if (!cookies.equals(other.cookies))
			return false;
		if (dsId == null) {
			if (other.dsId != null)
				return false;
		} else if (!dsId.equals(other.dsId))
			return false;
		if (endpoint == null) {
			if (other.endpoint != null)
				return false;
		} else if (!endpoint.equals(other.endpoint))
			return false;
		if (headers == null) {
			if (other.headers != null)
				return false;
		} else if (!headers.equals(other.headers))
			return false;
		return true;
	}

}
