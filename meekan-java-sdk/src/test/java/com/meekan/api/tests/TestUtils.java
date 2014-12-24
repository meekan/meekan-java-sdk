package com.meekan.api.tests;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.entities.Account;
import com.meekan.api.entities.User;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.params.ICloudNewAuthenticate;
import com.meekan.api.params.ICloudOldAuthenticate;
import com.meekan.api.utils.Utils;

public class TestUtils {
	public static final String API_KEY = "1234";
	public static final String AUTH_CONFIG_FILE = "config/auth_config";

	public static void checkResponse(ApiRequestResponse response) {
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) response.getMeta().getCode());
	}

	public static void printResult(ApiRequestResponse response) {
		if (response != null) {
			if (response.getMeta().getCode() == HttpURLConnection.HTTP_OK) {
				System.out.println(response.getResponse().toString());
			} else {
				System.out.println(response.getMeta().getErrorDetail());
			}
		}
	}

	public static String findAccountId(String email, User user) {
		List<Account> accounts = user.getAccounts();
		String accountId = null;
		for (Account account : accounts) {
			if (email.equals(account.getIdentifier())) {
				accountId = account.getId();
			}
		}
		return accountId;
	}

	public static HashMap<String, String> getIdentifierToAccount(MeekanApi meekanApi, Collection<String> idsOfAccounts) throws MeekanApiException,
			IOException, JsonParseException, JsonMappingException {
		ApiRequestResponse response = meekanApi.getIdsToAccounts(idsOfAccounts);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) response.getMeta().getCode());
		MapLikeType constructMapLikeType = Utils.getJSONObjectMapper().getTypeFactory()
				.constructMapLikeType(HashMap.class, String.class, String.class);
		HashMap<String, String> identifierToAccounts = Utils.getJSONObjectMapper().readValue(response.getResponse().get("data").toString(),
				constructMapLikeType);
		return identifierToAccounts;
	}

	public static ExchangeAuthenticate getExchangeAuthenticate() throws JsonProcessingException, IOException {
		JsonNode exchangeNode = Utils.getJSONObjectMapper().readTree(new File(AUTH_CONFIG_FILE)).get("exchange");
		return new ExchangeAuthenticate(exchangeNode.get("username").asText(), exchangeNode.get("email").asText(), exchangeNode.get("password")
				.asText(), exchangeNode.get("url").asText());
	}

	public static ICloudNewAuthenticate getICloudNewAuthenticate() throws JsonProcessingException, IOException {
		JsonNode jsonNode = Utils.getJSONObjectMapper().readTree(new File(AUTH_CONFIG_FILE)).get("new_icloud");
		return Utils.getJSONObjectMapper().readValue(jsonNode.toString(), ICloudNewAuthenticate.class);
	}

	public static ICloudOldAuthenticate getICloudAuthenticate() throws JsonProcessingException, IOException {
		JsonNode icloudNode = Utils.getJSONObjectMapper().readTree(new File(AUTH_CONFIG_FILE)).get("icloud");
		return new ICloudOldAuthenticate(icloudNode.get("username").asText(), icloudNode.get("password").asText(), icloudNode.get("icloudAppleId")
				.asText());
	}
}
