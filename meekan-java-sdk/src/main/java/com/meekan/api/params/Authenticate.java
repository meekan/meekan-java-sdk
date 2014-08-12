package com.meekan.api.params;

/**
 * Used to perform authenticate to the Meekan server
 * 
 * @author idog
 * @param <T>
 * 
 */
public interface Authenticate<T> extends MeekanApiParams<T> {

	public String getIdentifier();

	public String getProviderName();

}
