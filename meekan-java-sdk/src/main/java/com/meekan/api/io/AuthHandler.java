package com.meekan.api.io;

import com.meekan.api.ApiRequestResponse;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.params.ICloudAuthenticate;
import com.meekan.api.params.MeekanSessionCookies;

/**
 * Interface for all the authenticates for Meekan
 * 
 * @author idog
 * 
 */
public interface AuthHandler {

	/**
	 * @param authenticate
	 *            the icloud credentials
	 * @return the request response.
	 */
	public ApiRequestResponse icloudAuthenticate(ICloudAuthenticate authenticate);

	/**
	 * @param authenticate
	 *            the exchange credentials
	 * @return the request response.
	 */
	public ApiRequestResponse exchangeAuthenticate(ExchangeAuthenticate authenticate);

	/**
	 * @param cookies
	 *            - MeeekanCookies that came from other place (like webview)
	 */
	public void cookiesAuthenticate(MeekanSessionCookies cookies);
}
