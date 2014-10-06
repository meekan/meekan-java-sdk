package com.meekan.api.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
		CollectionLikeType constructCollectionLikeType = Utils.getJSONObjectMapper().getTypeFactory()
				.constructCollectionType(ArrayList.class, Slot.class);
		try {
			return Utils.getJSONObjectMapper().readValue(slotsResponse.getResponse().get("data").toString(), constructCollectionLikeType);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}

		return null;
	}

	public static HashMap<String, String> getIdentifierToAccount(MeekanApi meekanApi, Collection<String> idsOfAccounts) throws MeekanApiException {

		ApiRequestResponse response = meekanApi.getIdsToAccounts(idsOfAccounts);
		if (HttpURLConnection.HTTP_OK == (int) response.getMeta().getCode()) {
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

	public static String findAccountId(ApiRequestResponse authResponse, String email) {
		try {
			User user = Utils.getJSONObjectMapper().readValue(authResponse.getResponse().get("data").toString(), User.class);
			List<Account> accounts = user.getAccounts();
			String accountId = null;
			for (Account account : accounts) {
				if (email.equals(account.getIdentifier())) {
					accountId = account.getId();
					break;
				}
			}
			return accountId;
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}

		return null;

	}
}
