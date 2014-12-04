package com.meekan.api.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.entities.Account;
import com.meekan.api.entities.Slot;
import com.meekan.api.entities.User;

public class MeekanParseUtils {

	public static boolean checkApiCall(ApiRequestResponse response) {
		return response != null && response.getMeta() != null && response.getMeta().getCode() == HttpURLConnection.HTTP_OK;
	}

	public static List<Slot> getSlots(ApiRequestResponse slotsResponse) {
		if (slotsResponse.getMeta().getCode() == HttpURLConnection.HTTP_OK) {

			CollectionLikeType constructCollectionLikeType = Utils.getJSONObjectMapper().getTypeFactory()
					.constructCollectionType(ArrayList.class, Slot.class);
			try {
				JsonNode jsonNode = slotsResponse.getResponse().get("data");
				if (jsonNode != null) {
					return Utils.getJSONObjectMapper().readValue(jsonNode.toString(), constructCollectionLikeType);
				}
			} catch (JsonParseException e) {
			} catch (JsonMappingException e) {
			} catch (IOException e) {
			}
		}

		return null;
	}

	public static HashMap<String, String> getIdentifierToAccount(MeekanApi meekanApi, Collection<String> idsOfAccounts) throws MeekanApiException {
		ApiRequestResponse response = meekanApi.getIdsToAccounts(idsOfAccounts);
		return parseIdsToAccountResponse(response);
	}

	private static HashMap<String, String> parseIdsToAccountResponse(ApiRequestResponse response) throws MeekanApiException {
		if (HttpURLConnection.HTTP_OK == response.getMeta().getCode()) {
			MapLikeType constructMapLikeType = Utils.getJSONObjectMapper().getTypeFactory()
					.constructMapLikeType(HashMap.class, String.class, String.class);
			try {
				if (response.getResponse().has("data")) {

					String data = response.getResponse().get("data").toString();
					return Utils.getJSONObjectMapper().readValue(data, constructMapLikeType);
				}
			} catch (Exception e) {
				throw new MeekanApiException(e);
			}
		} else {
			throw new MeekanApiException(response.getMeta().getErrorDetail());
		}
		return new HashMap<String, String>();
	}

	public static HashMap<String, String> uploadContactsAndGetIdentifierToAccount(MeekanApi meekanApi, Collection<String> idsOfAccounts)
			throws MeekanApiException {

		ApiRequestResponse response = meekanApi.uploadContacts(idsOfAccounts);
		return parseIdsToAccountResponse(response);
	}

	public static User getUser(ApiRequestResponse authResponse) {
		if (authResponse.getMeta().getCode() == HttpURLConnection.HTTP_OK) {

			try {
				return Utils.getJSONObjectMapper().readValue(authResponse.getResponse().get("data").toString(), User.class);
			} catch (JsonParseException e) {
			} catch (JsonMappingException e) {
			} catch (IOException e) {
			}
		}

		return null;
	}

	public static String findAccountId(List<Account> accounts, String email, String accountType) {
		String accountId = null;
		for (Account account : accounts) {
			if (email.equals(account.getIdentifier()) && accountType.equals(account.getType())) {
				accountId = account.getId();
				break;
			}
		}
		return accountId;
	}
}
