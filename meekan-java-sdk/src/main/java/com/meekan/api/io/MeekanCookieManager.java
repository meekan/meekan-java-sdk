package com.meekan.api.io;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;

/**
 * Will ensure that will be only one instance of CookieManager
 * 
 * @author idog
 * 
 */
public class MeekanCookieManager extends CookieManager {

	private static CookieManager instance = null;

	private MeekanCookieManager(CookieStore store, CookiePolicy cookiePolicy) {
		super(store, cookiePolicy);
	}

	public static CookieManager getInstance() {
		if (instance == null) {
			synchronized (MeekanCookieManager.class) {
				if (instance == null) {
					instance = new MeekanCookieManager(null, CookiePolicy.ACCEPT_ALL);
				}
			}
		}
		return instance;

	}

}
