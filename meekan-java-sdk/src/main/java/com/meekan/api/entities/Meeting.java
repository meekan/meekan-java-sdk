package com.meekan.api.entities;

import java.util.List;
import java.util.Map;

public class Meeting implements MeekanEntity {

	private static final long serialVersionUID = 6956464216890122241L;

	private String notes;
	private String timezone;
	private String location_desc;
	private String location_latlong;
	private String location_address;
	private Integer reminder_minutes_before;
	private Double last_update;
	private Double create_time;
	private List<String> invitees;
	private Map<String, Vote> votes;
	private String reminder_method;
	private String time_desc;
	private String organizer_user;
	private List<RemoteTentativeId> remote_tentative_ids;
	private String organizer_email;
	private Integer repeat_interval;
	private List<Long> options;
	private String id;
	private Integer duration;
	private String organizer;
	private String name;
	private Boolean is_deleted;

	public String getTimezone() {
		return timezone;
	}

	public Double getLast_update() {
		return last_update;
	}

	public Double getCreate_time() {
		return create_time;
	}

	public List<String> getInvitees() {
		return invitees;
	}

	public Map<String, Vote> getVotes() {
		return votes;
	}

	public String getReminder_method() {
		return reminder_method;
	}

	public String getTime_desc() {
		return time_desc;
	}

	public String getOrganizer_user() {
		return organizer_user;
	}

	public List<RemoteTentativeId> getRemote_tentative_ids() {
		return remote_tentative_ids;
	}

	public String getOrganizer_email() {
		return organizer_email;
	}

	public Integer getRepeat_interval() {
		return repeat_interval;
	}

	public List<Long> getOptions() {
		return options;
	}

	public String getId() {
		return id;
	}

	public Integer getDuration() {
		return duration;
	}

	public String getOrganizer() {
		return organizer;
	}

	public String getName() {
		return name;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setLast_update(Double last_update) {
		this.last_update = last_update;
	}

	public void setCreate_time(Double create_time) {
		this.create_time = create_time;
	}

	public void setInvitees(List<String> invitees) {
		this.invitees = invitees;
	}

	public void setVotes(Map<String, Vote> votes) {
		this.votes = votes;
	}

	public void setReminder_method(String reminder_method) {
		this.reminder_method = reminder_method;
	}

	public void setTime_desc(String time_desc) {
		this.time_desc = time_desc;
	}

	public void setOrganizer_user(String organizer_user) {
		this.organizer_user = organizer_user;
	}

	public void setRemote_tentative_ids(List<RemoteTentativeId> remote_tentative_ids) {
		this.remote_tentative_ids = remote_tentative_ids;
	}

	public void setOrganizer_email(String organizer_email) {
		this.organizer_email = organizer_email;
	}

	public void setRepeat_interval(Integer repeat_interval) {
		this.repeat_interval = repeat_interval;
	}

	public void setOptions(List<Long> options) {
		this.options = options;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Integer getReminder_minutes_before() {
		return reminder_minutes_before;
	}

	public void setReminder_minutes_before(Integer reminder_minutes_before) {
		this.reminder_minutes_before = reminder_minutes_before;
	}

	public String getLocation_desc() {
		return location_desc;
	}

	public void setLocation_desc(String location_desc) {
		this.location_desc = location_desc;
	}

	public String getLocation_latlong() {
		return location_latlong;
	}

	public void setLocation_latlong(String location_latlong) {
		this.location_latlong = location_latlong;
	}

	public String getLocation_address() {
		return location_address;
	}

	public void setLocation_address(String location_address) {
		this.location_address = location_address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invitees == null) ? 0 : invitees.hashCode());
		result = prime * result + ((is_deleted == null) ? 0 : is_deleted.hashCode());
		result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
		result = prime * result + ((location_address == null) ? 0 : location_address.hashCode());
		result = prime * result + ((location_desc == null) ? 0 : location_desc.hashCode());
		result = prime * result + ((location_latlong == null) ? 0 : location_latlong.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((organizer == null) ? 0 : organizer.hashCode());
		result = prime * result + ((organizer_email == null) ? 0 : organizer_email.hashCode());
		result = prime * result + ((organizer_user == null) ? 0 : organizer_user.hashCode());
		result = prime * result + ((reminder_method == null) ? 0 : reminder_method.hashCode());
		result = prime * result + ((reminder_minutes_before == null) ? 0 : reminder_minutes_before.hashCode());
		result = prime * result + ((remote_tentative_ids == null) ? 0 : remote_tentative_ids.hashCode());
		result = prime * result + ((repeat_interval == null) ? 0 : repeat_interval.hashCode());
		result = prime * result + ((time_desc == null) ? 0 : time_desc.hashCode());
		result = prime * result + ((timezone == null) ? 0 : timezone.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
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
		Meeting other = (Meeting) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invitees == null) {
			if (other.invitees != null)
				return false;
		} else if (!invitees.equals(other.invitees))
			return false;
		if (is_deleted == null) {
			if (other.is_deleted != null)
				return false;
		} else if (!is_deleted.equals(other.is_deleted))
			return false;
		if (last_update == null) {
			if (other.last_update != null)
				return false;
		} else if (!last_update.equals(other.last_update))
			return false;
		if (location_address == null) {
			if (other.location_address != null)
				return false;
		} else if (!location_address.equals(other.location_address))
			return false;
		if (location_desc == null) {
			if (other.location_desc != null)
				return false;
		} else if (!location_desc.equals(other.location_desc))
			return false;
		if (location_latlong == null) {
			if (other.location_latlong != null)
				return false;
		} else if (!location_latlong.equals(other.location_latlong))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (organizer == null) {
			if (other.organizer != null)
				return false;
		} else if (!organizer.equals(other.organizer))
			return false;
		if (organizer_email == null) {
			if (other.organizer_email != null)
				return false;
		} else if (!organizer_email.equals(other.organizer_email))
			return false;
		if (organizer_user == null) {
			if (other.organizer_user != null)
				return false;
		} else if (!organizer_user.equals(other.organizer_user))
			return false;
		if (reminder_method == null) {
			if (other.reminder_method != null)
				return false;
		} else if (!reminder_method.equals(other.reminder_method))
			return false;
		if (reminder_minutes_before == null) {
			if (other.reminder_minutes_before != null)
				return false;
		} else if (!reminder_minutes_before.equals(other.reminder_minutes_before))
			return false;
		if (remote_tentative_ids == null) {
			if (other.remote_tentative_ids != null)
				return false;
		} else if (!remote_tentative_ids.equals(other.remote_tentative_ids))
			return false;
		if (repeat_interval == null) {
			if (other.repeat_interval != null)
				return false;
		} else if (!repeat_interval.equals(other.repeat_interval))
			return false;
		if (time_desc == null) {
			if (other.time_desc != null)
				return false;
		} else if (!time_desc.equals(other.time_desc))
			return false;
		if (timezone == null) {
			if (other.timezone != null)
				return false;
		} else if (!timezone.equals(other.timezone))
			return false;
		if (votes == null) {
			if (other.votes != null)
				return false;
		} else if (!votes.equals(other.votes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meeting [notes=" + notes + ", timezone=" + timezone + ", location_desc=" + location_desc + ", location_latlong=" + location_latlong
				+ ", location_address=" + location_address + ", reminder_minutes_before=" + reminder_minutes_before + ", last_update=" + last_update
				+ ", invitees=" + invitees + ", votes=" + votes + ", reminder_method=" + reminder_method + ", time_desc=" + time_desc
				+ ", organizer_user=" + organizer_user + ", remote_tentative_ids=" + remote_tentative_ids + ", organizer_email=" + organizer_email
				+ ", repeat_interval=" + repeat_interval + ", options=" + options + ", id=" + id + ", duration=" + duration + ", organizer="
				+ organizer + ", name=" + name + ", is_deleted=" + is_deleted + "]";
	}

}
