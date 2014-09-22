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
	private static CookieStore cookieStore = null;

	private MeekanCookieManager(CookieStore store, CookiePolicy cookiePolicy) {
		super(store, cookiePolicy);
	}

	public static void setCookieStore(CookieStore cookieStore) {
		MeekanCookieManager.cookieStore = cookieStore;

	}

	public static CookieManager getInstance() {
		if (instance == null) {
			synchronized (MeekanCookieManager.class) {
				if (instance == null) {
					instance = new MeekanCookieManager(cookieStore, CookiePolicy.ACCEPT_ALL);
				}
			}
		}
		return instance;

	}

}
