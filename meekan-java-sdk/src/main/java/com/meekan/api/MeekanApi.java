package com.meekan.api;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meekan.api.entities.Account;
import com.meekan.api.entities.User;
import com.meekan.api.io.ApiMethod;
import com.meekan.api.io.AuthHandler;
import com.meekan.api.io.IOHandler;
import com.meekan.api.io.MeekanAuthHandler;
import com.meekan.api.io.MeekanCookieManager;
import com.meekan.api.io.MeekanIOHandler;
import com.meekan.api.params.AccountAuth;
import com.meekan.api.params.Authenticate;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.params.GoogleAuthenticate;
import com.meekan.api.params.ICloudAuthenticate;
import com.meekan.api.params.MeekanSessionCookies;
import com.meekan.api.params.MeetingParam;
import com.meekan.api.params.MeetingVote;
import com.meekan.api.utils.HttpUtils;
import com.meekan.api.utils.SerializablePair;
import com.meekan.api.utils.Utils;

public class MeekanApi {

	public static final String UTF_8 = "UTF-8";
	// dontcommit
	public static final String API_URL = "http://192.168.1.29:8080/";
	// public static final String API_URL = "http://10.0.3.2:8080/";
	// public static final String API_URL = "https://playground.meekan.com/";
	// public static final String API_URL = "https://newmeeting.meekan.com/";

	public static URI API_URI;
	static {
		try {
			API_URI = new URI(MeekanApi.API_URL);
		} catch (URISyntaxException e) {
		}
	}

	private IOHandler ioHandler;
	private CookieManager cookieManager;
	private AuthHandler authHandler;
	private AccountAuth accountAuth;

	public MeekanApi(IOHandler ioHandler, AuthHandler authHandler) {
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
		this.authHandler = authHandler;
		this.ioHandler = ioHandler;
		cookieManager = MeekanCookieManager.getInstance();
		CookieHandler.setDefault(cookieManager);
		this.accountAuth = new AccountAuth();
	}

	public MeekanApi(String apiKey) {
		this(apiKey, null);
	}

	public MeekanApi(String apiKey, MeekanSessionCookies cookies) {
		this(apiKey, cookies, 0);
	}

	public MeekanApi(String apiKey, MeekanSessionCookies cookies, int timeoutInMillis) {
		this(new MeekanIOHandler(apiKey, timeoutInMillis), null);
		this.authHandler = new MeekanAuthHandler(this.ioHandler);
		if (cookies != null) {
			this.authHandler.cookiesAuthenticate(cookies);
		}
	}

	public void setAccountAuth(AccountAuth accountAuth) {
		this.accountAuth = accountAuth;
	}

	private ApiRequestResponse doApiRequest(ApiMethod method, String path, Map<String, Collection<String>> params) throws MeekanApiException {
		return doApiRequest(method, path, params, false);
	}

	/**
	 * wa
	 * 
	 * API Request
	 * 
	 * @param method
	 *            method used in request
	 * @param path
	 *            API endpoint
	 * @param auth
	 *            whether request should send oAuthToken or not
	 * @param params
	 *            request parameters.
	 * @param useAccountAuth
	 *            whether or not sending the account auth to server
	 * 
	 * @return response
	 * @throws JSONException
	 *             when JSON parsing error occurs
	 * @throws MeekanApiException
	 *             when something unexpected happens
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	private ApiRequestResponse doApiRequest(ApiMethod method, String path, Map<String, Collection<String>> params, boolean useAccountAuth)
			throws MeekanApiException {
		if (useAccountAuth && !accountAuth.isEmpty()) {
			if (ApiMethod.GET.equals(method)) {
				params.putAll(accountAuth.toParamsMap());
			} else {
				path += "?" + HttpUtils.createParams(accountAuth.toParamsMap());
			}
		}
		return HttpUtils.doApiRequest(method, path, params, ioHandler);
	}

	public ApiRequestResponse hello(String client, String version) throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		params.put("client", Collections.singleton(client));
		params.put("version", Collections.singleton(version));
		return doApiRequest(ApiMethod.GET, "rest/hello", params);
	}

	public ApiRequestResponse inviteUsers(Collection<String> emails) throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		params.put("emails", emails);
		return doApiRequest(ApiMethod.POST, "rest/invite", params);

	}

	public ApiRequestResponse updateUserProfile(Map<String, Collection<String>> params) throws MeekanApiException {
		return doApiRequest(ApiMethod.PUT, "rest/users/current", params);
	}

	/**
	 * Create a meeting for this specific account
	 */
	public ApiRequestResponse createMeeting(String accountId, MeetingParam meeting) throws MeekanApiException {
		Map<String, Collection<String>> params = meeting.toParamsMap();
		params.put("account_id", Collections.singleton(String.valueOf(accountId)));
		return doApiRequest(ApiMethod.POST, "rest/meetings", params, true);
	}

	/**
	 * Update a vote for a meeting to the authenticated user
	 */
	public ApiRequestResponse updateVoteForMeeting(String meetingId, String voterId, MeetingVote meetingVote, Collection<String> preferred)
			throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		if (Utils.isNotEmpty(preferred)) {
			params.put("preferred[]", preferred);
		}
		params.put("resp_type", Collections.singleton(String.valueOf(meetingVote.voteValue)));
		return doApiRequest(ApiMethod.PUT, String.format("rest/meetings/%s/poll/%s", meetingId, voterId), params, true);
	}

	/**
	 * Update a meeting
	 */
	public ApiRequestResponse updateMeeting(String meetingId, MeetingParam meeting) throws MeekanApiException {
		Map<String, Collection<String>> params = meeting.toParamsMap();
		return doApiRequest(ApiMethod.PUT, String.format("rest/meetings/%s", meetingId), params, true);
	}

	/**
	 * @return Adjacent events of this specific meeting of this account. currently support only on Google Calendar
	 */
	public ApiRequestResponse getAdjacentEvents(String meetingId, String accountId) throws MeekanApiException {
		return doApiRequest(ApiMethod.GET, String.format("rest/meetings/%s/poll/%s/adjacent-events", meetingId, accountId), null);
	}

	/**
	 * @return voter details for a meeting
	 */
	public ApiRequestResponse getVoterDetailsForMeeting(String meetingId, String voterId) throws MeekanApiException {
		return doApiRequest(ApiMethod.GET, String.format("rest/meetings/%s/poll/%s", meetingId, voterId), null);
	}

	/**
	 * @param idsOfAccounts
	 *            list of emails
	 * @return map between the founded emails to the accounts ids
	 */
	public ApiRequestResponse getIdsToAccounts(Collection<String> idsOfAccounts) throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		params.put("q[]", idsOfAccounts);
		return doApiRequest(ApiMethod.GET, "rest/accounts", params);
	}

	public ApiRequestResponse uploadContacts(Collection<String> idsOfAccounts) throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		params.put("contacts", idsOfAccounts);
		return doApiRequest(ApiMethod.PUT, "rest/users/current", params);
	}

	/**
	 * Delete authentication of specific provider (google, exchange, icloud) for the authenticated user
	 */
	public ApiRequestResponse deleteAuth(String providerName) throws MeekanApiException {
		return doApiRequest(ApiMethod.GET, String.format("social_login/%s/delete", providerName), null);
	}

	/**
	 * Find all slots in the given frames, and return sorted slots by rank.
	 * 
	 * @param invitees
	 *            - The account id of the invitees
	 * @param duration
	 *            - duration of the meeting in minutes
	 * @param organizerAccountId
	 *            - the account id of the organizer
	 * @param startEndFrames
	 *            - start end of frames to search slots
	 * @param page
	 *            - page of the slots
	 * 
	 */
	public ApiRequestResponse getSlots(Collection<String> invitees, int duration, String organizerAccountId,
			List<SerializablePair<Long, Long>> startEndFrames, String timezone, String timeDesc, String dayDesc, String locationLatLong,
			boolean useLocationPadding, int page) throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		params.put("invitees[]", invitees);
		params.put("duration", Collections.singleton(String.valueOf(duration)));
		params.put("organizer_account_id", Collections.singleton(organizerAccountId));
		Collection<String> frames = new ArrayList<String>();
		for (SerializablePair<Long, Long> startEndFrame : startEndFrames) {
			frames.add(String.format("%s:%s", startEndFrame.first, startEndFrame.second));
		}
		params.put("frames[]", frames);
		params.put("timezone", Collections.singleton(timezone));
		params.put("time_desc", Collections.singleton(timeDesc));
		params.put("day_desc", Collections.singleton(dayDesc));
		if (locationLatLong != null) {
			params.put("location_latlong", Collections.singleton(locationLatLong));
		}
		if (useLocationPadding) {
			params.put("use_location_padding", Collections.singleton(Boolean.toString(useLocationPadding)));
		}
		params.put("page", Collections.singleton(String.valueOf(page)));

		return doApiRequest(ApiMethod.POST, "rest/slots", params);
	}

	public ApiRequestResponse getSlots(Collection<String> invitees, int duration, String organizerAccountId,
			List<SerializablePair<Long, Long>> startEndFrames, String timezone, String timeDesc, String dayDesc, String locationLatLong,
			boolean useLocationPadding) throws MeekanApiException {
		return getSlots(invitees, duration, organizerAccountId, startEndFrames, timezone, timeDesc, dayDesc, locationLatLong, useLocationPadding, 0);
	}

	public ApiRequestResponse deleteAccount(String accountId) throws MeekanApiException {
		accountAuth.removeAccountAuth(accountId);
		return doApiRequest(ApiMethod.DELETE, "rest/accounts/" + accountId, null);
	}

	/**
	 * @return Delete meeting
	 */
	public ApiRequestResponse deleteMeeting(String meetingId) throws MeekanApiException {
		return doApiRequest(ApiMethod.DELETE, "rest/meetings/" + meetingId, null, true);
	}

	/**
	 * @return All meeting of the authenticated user from lastTimestamp
	 */
	public ApiRequestResponse getMeetings(long lastTimestamp) throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		params.put("last_timestamp", Collections.singleton(String.valueOf(lastTimestamp)));
		return doApiRequest(ApiMethod.GET, "rest/meetings", params);
	}

	/**
	 * @return all the freebusy times between start to end for accountId
	 */
	public ApiRequestResponse getFreeBusy(String accountId, long start, long end) throws MeekanApiException {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		params.put("min_date", Collections.singleton(String.valueOf(start)));
		params.put("max_date", Collections.singleton(String.valueOf(end)));
		return doApiRequest(ApiMethod.GET, String.format("rest/accounts/%s/freebusy", accountId), params);

	}

	public ApiRequestResponse googleAuthenticate(GoogleAuthenticate googleAuthenticate) {
		ApiRequestResponse response = authHandler.googleAuthenticate(googleAuthenticate);
		if (response.getMeta().getCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
			response = insertNewAuth(googleAuthenticate);
		}
		return response;
	}

	public ApiRequestResponse exchangeAuthenticate(ExchangeAuthenticate exchangeAuthenticate) {
		ApiRequestResponse response = authHandler.exchangeAuthenticate(exchangeAuthenticate);
		if (response.getMeta().getCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
			response = insertNewAuth(exchangeAuthenticate);
		}
		return response;
	}

	public ApiRequestResponse icloudAuthenticate(ICloudAuthenticate iCloudAuthenticate) {
		ApiRequestResponse response = authHandler.icloudAuthenticate(iCloudAuthenticate);
		if (response.getMeta().getCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
			response = insertNewAuth(iCloudAuthenticate);
		}
		return response;
	}

	public ApiRequestResponse insertNewAuth(Authenticate<?> authenticate) {
		ApiRequestResponse auth = getAuth();
		if (auth.getMeta().getCode() == HttpURLConnection.HTTP_OK) {
			try {
				User user = Utils.getJSONObjectMapper().readValue(auth.getResponse().get("data").toString(), User.class);
				String accountId = null;
				for (Account account : user.getAccounts()) {
					if (authenticate.getIdentifier().equals(account.getIdentifier())) {
						accountId = account.getId();
					}
				}

				accountAuth.addAccountAuth(accountId, authenticate);

			} catch (IOException e) {
				return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
			}
		}

		return auth;

	}

	public ApiRequestResponse getAuth() {
		try {
			return HttpUtils.doApiRequest(ApiMethod.GET, "rest/auth", null, ioHandler);
		} catch (MeekanApiException e) {
			return new ApiRequestResponse(new ResultMeta(500, "", e.getMessage()), null);
		}
	}
}
