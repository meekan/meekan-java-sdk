package com.meekan.api.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.meekan.api.ApiRequestResponse;
import com.meekan.api.MeekanApi;
import com.meekan.api.MeekanApiException;
import com.meekan.api.entities.Slot;
import com.meekan.api.entities.User;
import com.meekan.api.params.ICloudOldAuthenticate;
import com.meekan.api.params.MeetingParam;
import com.meekan.api.utils.SerializablePair;
import com.meekan.api.utils.Utils;

public class SlotsTest {

	private MeekanApi meekanApi;
	private String email;
	private User user;
	private String accountId;

	@Before
	public void setUp() throws Exception {
		meekanApi = new MeekanApi(TestUtils.API_KEY);
		ICloudOldAuthenticate iCloudAuthenticate = TestUtils.getICloudAuthenticate();
		email = iCloudAuthenticate.getICloudAppleId();
		ApiRequestResponse authResponse = meekanApi.icloudAuthenticate(iCloudAuthenticate);
		Assert.assertEquals(HttpURLConnection.HTTP_OK, (int) authResponse.getMeta().getCode());
		user = Utils.getJSONObjectMapper().readValue(authResponse.getResponse().get("data").toString(), User.class);
		accountId = TestUtils.findAccountId(email, user);
	}

	@Test
	public void testGetSlots() throws JsonParseException, JsonMappingException, MeekanApiException, IOException {
		long meetingTime = 1406729316L;
		List<String> options = Arrays.asList(String.valueOf(meetingTime));
		int durationInMinutes = 60;
		MeetingParam meeting = new MeetingParam().setAccountId(accountId).setDurationInMinutes(durationInMinutes).setMeetingName("test meeting")
				.setOptions(options);
		ApiRequestResponse createMeeting = meekanApi.createMeeting(accountId, meeting);
		TestUtils.checkResponse(createMeeting);
		Collection<String> idsOfAccounts = Arrays.asList(email);
		Collection<String> invitees = TestUtils.getIdentifierToAccount(meekanApi, idsOfAccounts).values();
		List<SerializablePair<Long, Long>> frames = Arrays.asList(new SerializablePair<Long, Long>(meetingTime, meetingTime
				+ (durationInMinutes * 100)));
		ApiRequestResponse slotsResponse = meekanApi.getSlots(invitees, 1, accountId, frames, "Asia/Jerusalem", "", "", null, false);
		TestUtils.checkResponse(slotsResponse);
		CollectionLikeType constructCollectionLikeType = Utils.getJSONObjectMapper().getTypeFactory()
				.constructCollectionType(ArrayList.class, Slot.class);
		List<Slot> slots = Utils.getJSONObjectMapper().readValue(slotsResponse.getResponse().get("data").toString(), constructCollectionLikeType);
		Assert.assertEquals(74680, (long) (slots.get(0).getRank()));
		Assert.assertEquals(accountId, String.valueOf(slots.get(slots.size() - 1).getNot_available().get(0)));
		int numberOfNotAvailableSlots = 0;
		for (Slot slot : slots) {
			if (slot.getNot_available().size() > 0) {
				numberOfNotAvailableSlots++;
			}
		}
		Assert.assertEquals(durationInMinutes / 5, numberOfNotAvailableSlots);
	}
}
