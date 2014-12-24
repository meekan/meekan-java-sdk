package com.meekan.api.entities;

import java.util.HashMap;
import java.util.List;

public class Preferences implements MeekanEntity {

	private static final long serialVersionUID = 9104552289562862275L;

	private List<Contact> available_contacts;
	private List<Period> available_periods;
	private List<Contact> busy_contacts;
	private List<Period> busy_periods;
	private int daily_meeting_limit;
	private List<DayRange> day_ranges;
	private List<String> disabled_email_keys;
	private List<String> disabled_push_keys;
	private Grouping grouping;
	private boolean is_ml_on;
	private boolean is_night_worker;
	private HashMap<String, Location> locations;

	private int paddingTime;
	private int people_limit;
	private String timezone;
	private List<Integer> weekends;
	private List<Integer> workdays;

	public Preferences() {
	}

	public List<Contact> getAvailable_contacts() {
		return available_contacts;
	}

	public Preferences setAvailable_contacts(List<Contact> available_contacts) {
		this.available_contacts = available_contacts;
		return this;
	}

	public List<Contact> getBusy_contacts() {
		return busy_contacts;
	}

	public Preferences setBusy_contacts(List<Contact> busy_contacts) {
		this.busy_contacts = busy_contacts;
		return this;
	}

	public List<Period> getAvailable_periods() {
		return available_periods;
	}

	public Preferences setAvailable_periods(List<Period> available_periods) {
		this.available_periods = available_periods;
		return this;
	}

	public List<Period> getBusy_periods() {
		return busy_periods;
	}

	public Preferences setBusy_periods(List<Period> busy_periods) {
		this.busy_periods = busy_periods;
		return this;
	}

	public int getDaily_meeting_limit() {
		return daily_meeting_limit;
	}

	public Preferences setDaily_meeting_limit(int daily_meeting_limit) {
		this.daily_meeting_limit = daily_meeting_limit;
		return this;
	}

	public List<DayRange> getDay_ranges() {
		return day_ranges;
	}

	public Preferences setDay_ranges(List<DayRange> day_ranges) {
		this.day_ranges = day_ranges;
		return this;
	}

	public List<String> getDisabled_email_keys() {
		return disabled_email_keys;
	}

	public Preferences setDisabled_email_keys(List<String> disabled_email_keys) {
		this.disabled_email_keys = disabled_email_keys;
		return this;
	}

	public List<String> getDisabled_push_keys() {
		return disabled_push_keys;
	}

	public Preferences setDisabled_push_keys(List<String> disabled_push_keys) {
		this.disabled_push_keys = disabled_push_keys;
		return this;
	}

	public Grouping getGrouping() {
		return grouping;
	}

	public Preferences setGrouping(Grouping grouping) {
		this.grouping = grouping;
		return this;
	}

	public boolean isIs_ml_on() {
		return is_ml_on;
	}

	public Preferences setIs_ml_on(boolean is_ml_on) {
		this.is_ml_on = is_ml_on;
		return this;
	}

	public boolean isIs_night_worker() {
		return is_night_worker;
	}

	public Preferences setIs_night_worker(boolean is_night_worker) {
		this.is_night_worker = is_night_worker;
		return this;
	}

	public HashMap<String, Location> getLocations() {
		return locations;
	}

	public Preferences setLocations(HashMap<String, Location> locations) {
		this.locations = locations;
		return this;
	}

	public int getPaddingTime() {
		return paddingTime;
	}

	public Preferences setPaddingTime(int paddingTime) {
		this.paddingTime = paddingTime;
		return this;
	}

	public int getPeople_limit() {
		return people_limit;
	}

	public Preferences setPeople_limit(int people_limit) {
		this.people_limit = people_limit;
		return this;
	}

	public String getTimezone() {
		return timezone;
	}

	public Preferences setTimezone(String timezone) {
		this.timezone = timezone;
		return this;
	}

	public List<Integer> getWeekends() {
		return weekends;
	}

	public Preferences setWeekends(List<Integer> weekends) {
		this.weekends = weekends;
		return this;
	}

	public List<Integer> getWorkdays() {
		return workdays;
	}

	public Preferences setWorkdays(List<Integer> workdays) {
		this.workdays = workdays;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available_contacts == null) ? 0 : available_contacts.hashCode());
		result = prime * result + ((available_periods == null) ? 0 : available_periods.hashCode());
		result = prime * result + ((busy_contacts == null) ? 0 : busy_contacts.hashCode());
		result = prime * result + ((busy_periods == null) ? 0 : busy_periods.hashCode());
		result = prime * result + daily_meeting_limit;
		result = prime * result + ((day_ranges == null) ? 0 : day_ranges.hashCode());
		result = prime * result + ((disabled_email_keys == null) ? 0 : disabled_email_keys.hashCode());
		result = prime * result + ((disabled_push_keys == null) ? 0 : disabled_push_keys.hashCode());
		result = prime * result + ((grouping == null) ? 0 : grouping.hashCode());
		result = prime * result + (is_ml_on ? 1231 : 1237);
		result = prime * result + (is_night_worker ? 1231 : 1237);
		result = prime * result + ((locations == null) ? 0 : locations.hashCode());
		result = prime * result + paddingTime;
		result = prime * result + people_limit;
		result = prime * result + ((timezone == null) ? 0 : timezone.hashCode());
		result = prime * result + ((weekends == null) ? 0 : weekends.hashCode());
		result = prime * result + ((workdays == null) ? 0 : workdays.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preferences other = (Preferences) obj;
		if (available_contacts == null) {
			if (other.available_contacts != null)
				return false;
		} else if (!available_contacts.equals(other.available_contacts))
			return false;
		if (available_periods == null) {
			if (other.available_periods != null)
				return false;
		} else if (!available_periods.equals(other.available_periods))
			return false;
		if (busy_contacts == null) {
			if (other.busy_contacts != null)
				return false;
		} else if (!busy_contacts.equals(other.busy_contacts))
			return false;
		if (busy_periods == null) {
			if (other.busy_periods != null)
				return false;
		} else if (!busy_periods.equals(other.busy_periods))
			return false;
		if (daily_meeting_limit != other.daily_meeting_limit)
			return false;
		if (day_ranges == null) {
			if (other.day_ranges != null)
				return false;
		} else if (!day_ranges.equals(other.day_ranges))
			return false;
		if (disabled_email_keys == null) {
			if (other.disabled_email_keys != null)
				return false;
		} else if (!disabled_email_keys.equals(other.disabled_email_keys))
			return false;
		if (disabled_push_keys == null) {
			if (other.disabled_push_keys != null)
				return false;
		} else if (!disabled_push_keys.equals(other.disabled_push_keys))
			return false;
		if (grouping != other.grouping)
			return false;
		if (is_ml_on != other.is_ml_on)
			return false;
		if (is_night_worker != other.is_night_worker)
			return false;
		if (locations == null) {
			if (other.locations != null)
				return false;
		} else if (!locations.equals(other.locations))
			return false;
		if (paddingTime != other.paddingTime)
			return false;
		if (people_limit != other.people_limit)
			return false;
		if (timezone == null) {
			if (other.timezone != null)
				return false;
		} else if (!timezone.equals(other.timezone))
			return false;
		if (weekends == null) {
			if (other.weekends != null)
				return false;
		} else if (!weekends.equals(other.weekends))
			return false;
		if (workdays == null) {
			if (other.workdays != null)
				return false;
		} else if (!workdays.equals(other.workdays))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Preferences [available_contacts=" + available_contacts + ", available_periods=" + available_periods + ", busy_contacts="
				+ busy_contacts + ", busy_periods=" + busy_periods + ", daily_meeting_limit=" + daily_meeting_limit + ", day_ranges=" + day_ranges
				+ ", disabled_email_keys=" + disabled_email_keys + ", disabled_push_keys=" + disabled_push_keys + ", grouping=" + grouping
				+ ", is_ml_on=" + is_ml_on + ", is_night_worker=" + is_night_worker + ", locations=" + locations + ", paddingTime=" + paddingTime
				+ ", people_limit=" + people_limit + ", timezone=" + timezone + ", weekends=" + weekends + ", workdays=" + workdays + "]";
	}

}
