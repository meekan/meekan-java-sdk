package com.meekan.api.params;

import java.util.Map;

/**
 * The MeekanApiParams used to get parameters to api calls
 * 
 * @author idog
 * 
 * @param <T>
 */
public interface MeekanApiParams<T> {

	public Map<String, T> toParamsMap();
}
