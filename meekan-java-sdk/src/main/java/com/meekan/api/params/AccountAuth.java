package com.meekan.api.params;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.meekan.api.utils.Utils;

public class AccountAuth implements MeekanApiParams<Collection<String>> {

	Map<String, Map<String, ?>> accountAuthMap;

	public AccountAuth() {
		accountAuthMap = new HashMap<String, Map<String, ?>>();
	}

	public void addAccountAuth(String accountId, Authenticate<?> authenticate) {
		Map<String, ?> paramsMap = authenticate.toParamsMap();
		if (Utils.isNotEmpty(paramsMap)) {
			accountAuthMap.put(accountId, paramsMap);
		}
	}

	public void removeAccountAuth(String accountId) {
		accountAuthMap.remove(accountId);
	}

	public boolean isEmpty() {
		return accountAuthMap.size() == 0;
	}

	@Override
	public Map<String, Collection<String>> toParamsMap() {
		Map<String, Collection<String>> res = null;
		if (Utils.isNotEmpty(accountAuthMap)) {
			res = new HashMap<String, Collection<String>>();
			res.put("account_auth", Collections.singleton(Utils.writeValueAsString(accountAuthMap)));
		}
		return res;
	}

}
