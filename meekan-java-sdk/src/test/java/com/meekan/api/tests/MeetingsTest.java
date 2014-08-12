package com.meekan.api.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.entities.Meeting;
import com.meekan.api.entities.User;
import com.meekan.api.params.ExchangeAuthenticate;
import com.meekan.api.params.MeetingParam;
import com.meekan.api.utils.Utils;

public class MeetingsTest {

	private MeekanApi meekanApi;

	@Before
	public void setUp() throws Exception {
		meekanApi = new MeekanApi(TestUtils.API_KEY);
	}

	@Test
	public void testCreatingAndUpdatingMeeting() throws JsonParseException, JsonMappingException, IOException, MeekanApiException {
		// authenticate to meekan through exchange
		ExchangeAuthenticate exchangeAuthenticate = TestUtils.getExchangeAuthenticate();
		String email = exchangeAuthenticate.getEmail();
		ApiRequestResponse result = meekanApi.exchangeAuthenticate(exchangeAuthenticate);
		Assert.assertEquals(200, (int) result.getMeta().getCode());
		User user = Utils.getJSONObjectMapper().readValue(result.getResponse().get("data").toString(), User.class);
		Assert.assertEquals(email, user.getPrimary_email());

		// check that the account fit
		String accountId = TestUtils.findAccountId(email, user);

		Assert.assertNotNull(accountId);

		// create meeting
		long option1 = 1402392132;
		List<String> options = Arrays.asList(String.valueOf(option1), "1402395500");
		List<String> emails = Arrays.asList("ido@callapp.com");
		String meetingName = "test meeting" + new Random().nextInt();
		MeetingParam meeting = new MeetingParam().setMeetingName(meetingName).setAccountId(accountId).setDurationInMinutes(60).setOptions(options)
				.setInviteesEmails(emails);
		ApiRequestResponse meetingResponse = meekanApi.createMeeting(accountId, meeting);

		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) meetingResponse.getMeta().getCode());
		String meetingId = meetingResponse.getResponse().get("data").get("meeting_id").asText();

		// update meeting
		List<String> options2 = new ArrayList<String>(options);
		options2.remove(1);
		meeting.setOptions(options2);
		String newMeetingName = meetingName + " - updated";
		meeting.setMeetingName(newMeetingName);
		result = meekanApi.updateMeeting(meetingId, meeting);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) result.getMeta().getCode());

		// check that the meeting exists
		result = meekanApi.getMeetings(option1 - 1000);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) result.getMeta().getCode());
		CollectionType constructCollectionType = Utils.getJSONObjectMapper().getTypeFactory().constructCollectionType(List.class, Meeting.class);
		List<Meeting> meetingResList = Utils.getJSONObjectMapper().readValue(result.getResponse().get("data").get("meetings").toString(),
				constructCollectionType);
		Meeting foundMeeting = null;
		for (Meeting meetingRes : meetingResList) {
			if (meetingId.equals(meetingRes.getId())) {
				foundMeeting = meetingRes;
				break;
			}
		}
		Assert.assertNotNull(foundMeeting);
		Assert.assertEquals(newMeetingName, foundMeeting.getName());

		// check deleting meeting
		result = meekanApi.deleteMeeting(meetingId);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) result.getMeta().getCode());
		result = meekanApi.getMeetings(option1 - 1000);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) result.getMeta().getCode());
		meetingResList = Utils.getJSONObjectMapper().readValue(result.getResponse().get("data").get("meetings").toString(), constructCollectionType);
		foundMeeting = null;
		for (Meeting meetingRes : meetingResList) {
			if (meetingId.equals(meetingRes.getId())) {
				foundMeeting = meetingRes;
				break;
			}
		}
		Assert.assertTrue(foundMeeting.getIs_deleted());
	}
}
