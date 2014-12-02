package com.meekan.api.params;

import java.util.HashMap;
import java.util.Map;

public class ICloudNewAuthenticate implements ICloudAuthenticate<String> {
	private static final long serialVersionUID = 77354245414738913L;

	public static final String ICLOUD = "icloud";

	private String appleId;
	private String password;
	private String mmeAuth;
	private String dsprsid;

	public ICloudNewAuthenticate(String appleId, String password) {
		this.appleId = appleId;
		this.password = password;
	}

	public ICloudNewAuthenticate() {
		// empty ctor
	}

	public void setMmeAuthAndDsprsid(String mmeAuth, String dsprsid) {
		this.mmeAuth = mmeAuth;
		this.dsprsid = dsprsid;
	}

	@Override
	public Map<String, String> toParamsMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mme", mmeAuth);
		params.put("dsprsid", dsprsid);
		params.put("appleId", appleId);
		return params;
	}

	@Override
	public String getIdentifier() {
		return appleId;
	}

	@Override
	public String getProviderName() {
		return ICLOUD;
	}

	public String getAppleId() {
		return appleId;
	}

	public void setAppleId(String appleId) {
		this.appleId = appleId;
	}

	public String getPassword() {
		return password;
	}

	public String getMmeAuth() {
		return mmeAuth;
	}

	public String getDsprsid() {
		return dsprsid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMmeAuth(String mmeAuth) {
		this.mmeAuth = mmeAuth;
	}

	public void setDsprsid(String dsprsid) {
		this.dsprsid = dsprsid;
	}

}
