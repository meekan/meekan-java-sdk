package com.meekan.api.params;

import java.util.HashMap;
import java.util.Map;

public class ICloudNewAuthenticate implements ICloudAuthenticate<String> {
	public static final String ICLOUD = "icloud";

	private String iCloudAppleId;
	private String password;
	private String mmeAuth;
	private String dsprsid;

	public ICloudNewAuthenticate(String iCloudAppleId, String password) {
		this.iCloudAppleId = iCloudAppleId;
		this.password = password;
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
		params.put("iCloudAppleId", iCloudAppleId);
		return params;
	}

	@Override
	public String getIdentifier() {
		return iCloudAppleId;
	}

	@Override
	public String getProviderName() {
		return ICLOUD;
	}

	public String getiCloudAppleId() {
		return iCloudAppleId;
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

	public void setiCloudAppleId(String iCloudAppleId) {
		this.iCloudAppleId = iCloudAppleId;
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
