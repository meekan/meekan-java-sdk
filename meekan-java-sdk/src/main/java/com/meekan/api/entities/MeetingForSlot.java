package com.meekan.api.entities;

public class MeetingForSlot implements MeekanEntity {

	private static final long serialVersionUID = -5355863894894843276L;

	private String meeting_name;
	private Long start;
	private Integer duration;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((meeting_name == null) ? 0 : meeting_name.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		MeetingForSlot other = (MeetingForSlot) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (meeting_name == null) {
			if (other.meeting_name != null)
				return false;
		} else if (!meeting_name.equals(other.meeting_name))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	public String getMeeting_name() {
		return meeting_name;
	}

	public void setMeeting_name(String meeting_name) {
		this.meeting_name = meeting_name;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "MeetingForSlot [meeting_name=" + meeting_name + ", start=" + start + ", duration=" + duration + "]";
	}

}
