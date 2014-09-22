package com.meekan.api.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.entities.User;
import com.meekan.api.params.ICloudOldAuthenticate;
import com.meekan.api.params.MeetingParam;
import com.meekan.api.utils.Utils;

public class FreeBusyTest {

	private MeekanApi meekanApi;

	@Before
	public void setUp() throws Exception {
		meekanApi = new MeekanApi(TestUtils.API_KEY);
	}

	@Test
	public void testGetICloudFreeBusy() throws MeekanApiException, JsonParseException, JsonMappingException, IOException {
		ICloudOldAuthenticate iCloudAuthenticate = TestUtils.getICloudAuthenticate();
		String email = iCloudAuthenticate.getICloudAppleId();
		ApiRequestResponse authResponse = meekanApi.icloudAuthenticate(iCloudAuthenticate);

		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) authResponse.getMeta().getCode());
		User user = Utils.getJSONObjectMapper().readValue(authResponse.getResponse().get("data").toString(), User.class);
		String accountId = TestUtils.findAccountId(email, user);
		createMeetingCheckFreeBusyAndDelete(accountId);
	}

	/**
	 * Creating event, check that this event will be in the freebusy, and delete it.
	 */
	private void createMeetingCheckFreeBusyAndDelete(String accountId) throws MeekanApiException {
		long meetingTime = 1310379420; // 11.7.2011 10:17
		List<String> options = Arrays.asList(String.valueOf(meetingTime));
		int durationInMinutes = 60;
		MeetingParam meeting = new MeetingParam().setAccountId(accountId).setDurationInMinutes(durationInMinutes).setMeetingName("test meeting")
				.setOptions(options);
		ApiRequestResponse createMeeting = meekanApi.createMeeting(accountId, meeting);
		TestUtils.checkResponse(createMeeting);
		ApiRequestResponse freeBusy = meekanApi.getFreeBusy(accountId, meetingTime - (durationInMinutes * 100), meetingTime
				+ (durationInMinutes * 100));
		TestUtils.checkResponse(freeBusy);

		try {
			long start = freeBusy.getResponse().get("data").get(0).get("start").asLong();
			long end = freeBusy.getResponse().get("data").get(0).get("end").asLong();
			Assert.assertEquals(meetingTime, start);
			Assert.assertEquals(meetingTime + (durationInMinutes * 60), end);
		} finally {

			String meetingId = createMeeting.getResponse().get("data").get("meeting_id").asText();
			ApiRequestResponse deleteMeeting = meekanApi.deleteMeeting(meetingId);
			TestUtils.checkResponse(deleteMeeting);
		}

	}
}
