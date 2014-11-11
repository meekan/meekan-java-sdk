package com.meekan.api.params;

import static com.meekan.api.utils.Utils.putIfNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meekan.api.utils.SerializablePair;
import com.meekan.api.utils.Utils;

/**
 * This class represent all the information on a meeting
 * 
 * @author idog
 * 
 */
public class MeetingParam implements MeekanApiParams<Collection<String>> {
	private List<String> inviteesEmails;
	private List<String> inviteesKeys;
	private Integer durationInMinutes;
	private String timeSlotDescription;
	private List<String> slots;
	private String timezone;
	private String meetingName;
	private String locationDesc;
	private String locationAddress;
	private SerializablePair<Double, Double> latLng;
	private Integer reminderMinutesBefore;
	private String reminderMethod;
	private RepeatInterval repeatInterval;
	private String calendarId;
	private List<String> options;
	private String accountId;

	public MeetingParam setInviteesEmails(List<String> inviteesEmails) {
		this.inviteesEmails = inviteesEmails;
		return this;
	}

	public MeetingParam setInviteesKeys(List<String> inviteesKeys) {
		this.inviteesKeys = inviteesKeys;
		return this;
	}

	public MeetingParam setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
		return this;
	}

	public MeetingParam setTimeSlotDescription(String timeSlotDescription) {
		this.timeSlotDescription = timeSlotDescription;
		return this;
	}

	public MeetingParam setSlots(List<String> slots) {
		this.slots = slots;
		return this;
	}

	public MeetingParam setTimezone(String timezone) {
		this.timezone = timezone;
		return this;
	}

	public MeetingParam setMeetingName(String meetingName) {
		this.meetingName = meetingName;
		return this;
	}

	public MeetingParam setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
		return this;
	}

	public MeetingParam setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
		return this;
	}

	public MeetingParam setLatLng(SerializablePair<Double, Double> latLng) {
		this.latLng = latLng;
		return this;
	}

	public MeetingParam setReminderMinutesBefore(Integer reminderMinutesBefore) {
		this.reminderMinutesBefore = reminderMinutesBefore;
		return this;
	}

	public MeetingParam setReminderMethod(String reminderMethod) {
		this.reminderMethod = reminderMethod;
		return this;
	}

	public MeetingParam setRepeatInterval(RepeatInterval repeatInterval) {
		this.repeatInterval = repeatInterval;
		return this;
	}

	public MeetingParam setCalendarId(String calendarId) {
		this.calendarId = calendarId;
		return this;
	}

	public MeetingParam setOptions(List<String> options) {
		this.options = options;
		return this;
	}

	public MeetingParam setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((calendarId == null) ? 0 : calendarId.hashCode());
		result = prime * result + ((durationInMinutes == null) ? 0 : durationInMinutes.hashCode());
		result = prime * result + ((inviteesEmails == null) ? 0 : inviteesEmails.hashCode());
		result = prime * result + ((inviteesKeys == null) ? 0 : inviteesKeys.hashCode());
		result = prime * result + ((latLng == null) ? 0 : latLng.hashCode());
		result = prime * result + ((locationAddress == null) ? 0 : locationAddress.hashCode());
		result = prime * result + ((locationDesc == null) ? 0 : locationDesc.hashCode());
		result = prime * result + ((meetingName == null) ? 0 : meetingName.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((reminderMethod == null) ? 0 : reminderMethod.hashCode());
		result = prime * result + ((reminderMinutesBefore == null) ? 0 : reminderMinutesBefore.hashCode());
		result = prime * result + ((repeatInterval == null) ? 0 : repeatInterval.hashCode());
		result = prime * result + ((slots == null) ? 0 : slots.hashCode());
		result = prime * result + ((timeSlotDescription == null) ? 0 : timeSlotDescription.hashCode());
		result = prime * result + ((timezone == null) ? 0 : timezone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MeetingParam))
			return false;
		MeetingParam other = (MeetingParam) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (calendarId == null) {
			if (other.calendarId != null)
				return false;
		} else if (!calendarId.equals(other.calendarId))
			return false;
		if (durationInMinutes == null) {
			if (other.durationInMinutes != null)
				return false;
		} else if (!durationInMinutes.equals(other.durationInMinutes))
			return false;
		if (inviteesEmails == null) {
			if (other.inviteesEmails != null)
				return false;
		} else if (!inviteesEmails.equals(other.inviteesEmails))
			return false;
		if (inviteesKeys == null) {
			if (other.inviteesKeys != null)
				return false;
		} else if (!inviteesKeys.equals(other.inviteesKeys))
			return false;
		if (latLng == null) {
			if (other.latLng != null)
				return false;
		} else if (!latLng.equals(other.latLng))
			return false;
		if (locationAddress == null) {
			if (other.locationAddress != null)
				return false;
		} else if (!locationAddress.equals(other.locationAddress))
			return false;
		if (locationDesc == null) {
			if (other.locationDesc != null)
				return false;
		} else if (!locationDesc.equals(other.locationDesc))
			return false;
		if (meetingName == null) {
			if (other.meetingName != null)
				return false;
		} else if (!meetingName.equals(other.meetingName))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (reminderMethod == null) {
			if (other.reminderMethod != null)
				return false;
		} else if (!reminderMethod.equals(other.reminderMethod))
			return false;
		if (reminderMinutesBefore == null) {
			if (other.reminderMinutesBefore != null)
				return false;
		} else if (!reminderMinutesBefore.equals(other.reminderMinutesBefore))
			return false;
		if (repeatInterval != other.repeatInterval)
			return false;
		if (slots == null) {
			if (other.slots != null)
				return false;
		} else if (!slots.equals(other.slots))
			return false;
		if (timeSlotDescription == null) {
			if (other.timeSlotDescription != null)
				return false;
		} else if (!timeSlotDescription.equals(other.timeSlotDescription))
			return false;
		if (timezone == null) {
			if (other.timezone != null)
				return false;
		} else if (!timezone.equals(other.timezone))
			return false;
		return true;
	}

	@Override
	public Map<String, Collection<String>> toParamsMap() {
		Map<String, Collection<String>> params = new HashMap<String, Collection<String>>();
		if (Utils.isNotEmpty(inviteesEmails)) {
			params.put("e_inv[]", inviteesEmails);
		}

		if (Utils.isNotEmpty(inviteesKeys)) {
			params.put("k_inv[]", inviteesKeys);
		}

		putIfNotNull(params, "duration", durationInMinutes);
		putIfNotNull(params, "time_slots_desc", timeSlotDescription);
		if (Utils.isNotEmpty(slots)) {
			params.put("slot[]", slots);
		}

		putIfNotNull(params, "timezone", timezone);
		putIfNotNull(params, "meeting_name", meetingName);
		putIfNotNull(params, "location_desc", locationDesc);
		putIfNotNull(params, "location_address", locationAddress);
		if (latLng != null) {
			params.put("location_latlong", Collections.singleton(String.format("%s,%s", latLng.first, latLng.second)));
		}
		putIfNotNull(params, "reminder_minutes_before", reminderMinutesBefore);
		putIfNotNull(params, "reminder_method", reminderMethod);
		if (repeatInterval != null) {
			params.put("repeat_interval", Collections.singleton(String.valueOf(repeatInterval.intervalValue)));
		}

		putIfNotNull(params, "calendar_id", calendarId);
		if (Utils.isNotEmpty(options)) {
			params.put("opt[]", options);
		}
		putIfNotNull(params, "account_id", accountId);

		return params;
	}

	/**
	 * Enum that represent the repeating interval for meetings
	 * 
	 * @author idog
	 * 
	 */
	public enum RepeatInterval {

		NOT_REPEATING(0),
		DAILY(1),
		WEEKLY(7),
		BIWEEKLY(14),
		MONTHLY(31),
		YEARLY(365);

		public final int intervalValue;

		RepeatInterval(int intervalValue) {
			this.intervalValue = intervalValue;
		}

		public static RepeatInterval fromValue(int intervalValue) {
			switch (intervalValue) {
			case 0:
				return NOT_REPEATING;
			case 1:
				return DAILY;
			case 7:
				return WEEKLY;
			case 14:
				return BIWEEKLY;
			case 31:
				return MONTHLY;
			case 365:
				return YEARLY;
			default:
				return NOT_REPEATING;
			}
		}
	}
}
