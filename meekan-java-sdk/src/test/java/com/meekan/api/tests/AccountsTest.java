package com.meekan.api.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.entities.User;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.utils.Utils;

public class AccountsTest {

	private MeekanApi meekanApi;
	private String email;
	private User user;

	@Before
	public void setUp() throws Exception {
		meekanApi = new MeekanApi(TestUtils.API_KEY);
		ExchangeAuthenticate exchangeAuthenticate = TestUtils.getExchangeAuthenticate();
		email = exchangeAuthenticate.getEmail();
		ApiRequestResponse authResponse = meekanApi.exchangeAuthenticate(exchangeAuthenticate);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) authResponse.getMeta().getCode());
		user = Utils.getJSONObjectMapper().readValue(authResponse.getResponse().get("data").toString(), User.class);
	}

	@Test
	public void testDeleteAccount() throws MeekanApiException, JsonParseException, JsonMappingException, IOException {
		String accountId = TestUtils.findAccountId(email, user);
		ApiRequestResponse deleteAccountResponse = meekanApi.deleteAccount(accountId);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) deleteAccountResponse.getMeta().getCode());
		ApiRequestResponse auth = meekanApi.getAuth();
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) auth.getMeta().getCode());
		User userAfterDelete = Utils.getJSONObjectMapper().readValue(auth.getResponse().get("data").toString(), User.class);
		String findAccountId = TestUtils.findAccountId(email, userAfterDelete);
		Assert.assertNull(findAccountId);
	}

	@Test
	public void testGetIdentifiers() throws MeekanApiException, JsonParseException, JsonMappingException, IOException {
		Collection<String> idsOfAccounts = Arrays.asList(email);
		HashMap<String, String> identifierToAccounts = TestUtils.getIdentifierToAccount(meekanApi, idsOfAccounts);
		String accountId = TestUtils.findAccountId(email, user);
		Assert.assertEquals(accountId, identifierToAccounts.get(email));
	}

}
