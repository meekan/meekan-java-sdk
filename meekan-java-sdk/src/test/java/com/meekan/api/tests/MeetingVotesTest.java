package com.meekan.api.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.entities.User;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.params.MeetingParam;
import com.meekan.api.params.MeetingVote;
import com.meekan.api.utils.Utils;

public class MeetingVotesTest {

	private MeekanApi meekanApi;
	private String email;
	private User user;
	private String accountId;

	@Before
	public void setUp() throws Exception {
		meekanApi = new MeekanApi(TestUtils.API_KEY);
		ExchangeAuthenticate exchangeAuthenticate = TestUtils.getExchangeAuthenticate();
		email = exchangeAuthenticate.getEmail();
		ApiRequestResponse authResponse = meekanApi.exchangeAuthenticate(exchangeAuthenticate);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) authResponse.getMeta().getCode());
		user = Utils.getJSONObjectMapper().readValue(authResponse.getResponse().get("data").toString(), User.class);
		accountId = TestUtils.findAccountId(email, user);
	}

	@Test
	public void testUpdateVoter() throws MeekanApiException, JsonParseException, JsonProcessingException, IOException {
		List<String> options = Arrays.asList("1407427560", "1407513960", "1407600360");
		List<String> inviteesEmails = Arrays.asList("ido@callapp.com");
		MeetingParam meeting = new MeetingParam().setAccountId(accountId).setDurationInMinutes(60).setMeetingName("test meeting").setOptions(options)
				.setInviteesEmails(inviteesEmails);
		ApiRequestResponse createMeeting = meekanApi.createMeeting(accountId, meeting);
		TestUtils.checkResponse(createMeeting);
		String meetingId = createMeeting.getResponse().get("data").get("meeting_id").asText();
		ApiRequestResponse idsToAccounts = meekanApi.getIdsToAccounts(inviteesEmails);
		TestUtils.checkResponse(idsToAccounts);
		MapLikeType constructMapLikeType = Utils.getJSONObjectMapper().getTypeFactory()
				.constructMapLikeType(HashMap.class, String.class, String.class);
		HashMap<String, String> identifierToAccounts = Utils.getJSONObjectMapper().readValue(idsToAccounts.getResponse().get("data").toString(),
				constructMapLikeType);
		String voterAccountId = identifierToAccounts.get(inviteesEmails.get(0));
		MeetingVote respType;
		List<String> optionsVotes;
		if (new Random().nextBoolean()) {
			optionsVotes = new ArrayList<String>();
			respType = MeetingVote.CUSTOM;
			for (String option : options) {
				if (new Random().nextBoolean()) {
					optionsVotes.add(option);
				}
			}
		} else {
			respType = MeetingVote.NO;
			optionsVotes = options;
		}

		ApiRequestResponse updateVoteForMeeting = meekanApi.updateVoteForMeeting(meetingId, voterAccountId, respType, optionsVotes);
		TestUtils.checkResponse(updateVoteForMeeting);
		ApiRequestResponse voterDetailsForMeeting = meekanApi.getVoterDetailsForMeeting(meetingId, voterAccountId);
		TestUtils.checkResponse(voterDetailsForMeeting);
		JsonNode userVote = voterDetailsForMeeting.getResponse().get("data").get("user_vote");
		Assert.assertEquals(respType.voteValue, userVote.get("type").asInt());
		List<Integer> times = Utils.getJSONObjectMapper().readValue(userVote.get("times").toString(), List.class);
		for (String option : optionsVotes) {
			int indexOf = options.indexOf(option);
			int resRespType = times.get(indexOf);
			Assert.assertEquals(respType.voteValue, resRespType);
		}

	}
}
