package com.meekan.api.io;

import java.util.Collection;
import java.util.Map;

/**
 * Interface for handling IO from server
 * 
 * @author idog
 * 
 */
public interface IOHandler {

	/**
	 * @param url
	 *            - The url of the api call
	 * @param method
	 *            - The method
	 * @param params
	 *            - map between the param key to param value
	 * @return The response build by the returned values
	 */
	public Response fetchData(String url, ApiMethod method, Map<String, Collection<String>> params);
}
