package com.meekan.api.io;

import com.meekan.api.ApiRequestResponse;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.params.GoogleAuthenticate;
import com.meekan.api.params.ICloudAuthenticate;
import com.meekan.api.params.MeekanSessionCookies;
import com.meekan.api.params.Office365Authenticate;

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

	public ApiRequestResponse googleAuthenticate(GoogleAuthenticate googleAuthenticate);

	/**
	 * @param cookies
	 *            - MeeekanCookies that came from other place (like webview)
	 */
	public void cookiesAuthenticate(MeekanSessionCookies cookies);

	public ApiRequestResponse office365Authenticate(Office365Authenticate officeAuthenticate);

}
