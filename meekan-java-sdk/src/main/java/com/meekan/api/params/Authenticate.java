package com.meekan.api.params;

import java.io.Serializable;

/**
 * Used to perform authenticate to the Meekan server
 * 
 * @author idog
 * @param <T>
 * 
 */
public interface Authenticate<T> extends MeekanApiParams<T>, Serializable {

	public String getIdentifier();

	public String getProviderName();

}
