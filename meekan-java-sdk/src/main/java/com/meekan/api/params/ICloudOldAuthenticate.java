package com.meekan.api.params;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use this class to authenticate icloud.
 * 
 * @author idog
 * 
 */
public class ICloudOldAuthenticate implements ICloudAuthenticate<Object> {

	private static final long serialVersionUID = 4436622421429826273L;
	public static final String ICLOUD = "icloud";
	private String username;
	private String password;
	private String icloudAppleId;
	private ICloudServerEntity iCloudServerEntity;

	/**
	 * @param username
	 *            - username for icloud
	 * @param password
	 *            - password for icloud
	 * @param icloudAppleId
	 *            - the icloud apple id
	 */
	public ICloudOldAuthenticate(String username, String password, String icloudAppleId) {
		this.username = username;
		this.password = password;
		this.icloudAppleId = icloudAppleId;
	}

	public ICloudOldAuthenticate() {
		// empty ctor
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getICloudAppleId() {
		return icloudAppleId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIcloudAppleId(String icloudAppleId) {
		this.icloudAppleId = icloudAppleId;
	}

	@Override
	public Map<String, Object> toParamsMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("iCloudAppleId", getICloudAppleId());
		params.put("params", getiCloudServerEntity().getParams());
		List<HttpCookie> cookies = getiCloudServerEntity().getCookies();
		StringBuilder cookie = new StringBuilder();
		for (HttpCookie httpCookie : cookies) {
			cookie.append(httpCookie.getName());
			cookie.append("=");
			cookie.append(httpCookie.getValue());
			cookie.append(";");
		}
		cookie.deleteCharAt(cookie.length() - 1);
		params.put("cookies", cookie.toString());
		params.put("headers", getiCloudServerEntity().getHeaders());
		params.put("endpoint", getiCloudServerEntity().getEndpoint());

		return params;
	}

	@Override
	public String getProviderName() {
		return ICLOUD;
	}

	@Override
	public String getIdentifier() {
		return getICloudAppleId();
	}

	public ICloudServerEntity getiCloudServerEntity() {
		return iCloudServerEntity;
	}

	public void setiCloudServerEntity(ICloudServerEntity iCloudServerEntity) {
		this.iCloudServerEntity = iCloudServerEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iCloudServerEntity == null) ? 0 : iCloudServerEntity.hashCode());
		result = prime * result + ((icloudAppleId == null) ? 0 : icloudAppleId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ICloudOldAuthenticate))
			return false;
		ICloudOldAuthenticate other = (ICloudOldAuthenticate) obj;
		if (iCloudServerEntity == null) {
			if (other.iCloudServerEntity != null)
				return false;
		} else if (!iCloudServerEntity.equals(other.iCloudServerEntity))
			return false;
		if (icloudAppleId == null) {
			if (other.icloudAppleId != null)
				return false;
		} else if (!icloudAppleId.equals(other.icloudAppleId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
