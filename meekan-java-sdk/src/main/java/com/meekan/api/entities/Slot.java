package com.meekan.api.entities;

import java.util.List;

public class Slot implements MeekanEntity {

	private static final long serialVersionUID = 6055752784018268597L;

	private List<Long> not_available;
	private Long start;
	private Long rank;
	private Integer padding_before;
	private Integer padding_after;
	private MeetingForSlot meeting_before;
	private MeetingForSlot meeting_after;

	public MeetingForSlot getMeeting_before() {
		return meeting_before;
	}

	public void setMeeting_before(MeetingForSlot meeting_before) {
		this.meeting_before = meeting_before;
	}

	public MeetingForSlot getMeeting_after() {
		return meeting_after;
	}

	public void setMeeting_after(MeetingForSlot meeting_after) {
		this.meeting_after = meeting_after;
	}

	public List<Long> getNot_available() {
		return not_available;
	}

	public Long getStart() {
		return start;
	}

	public Long getRank() {
		return rank;
	}

	public Slot setNot_available(List<Long> not_available) {
		this.not_available = not_available;
		return this;
	}

	public Slot setStart(Long start) {
		this.start = start;
		return this;
	}

	public Slot setRank(Long rank) {
		this.rank = rank;
		return this;
	}

	public Integer getPadding_before() {
		return padding_before;
	}

	public Slot setPadding_before(Integer padding_before) {
		this.padding_before = padding_before;
		return this;
	}

	public Integer getPadding_after() {
		return padding_after;
	}

	public Slot setPadding_after(Integer padding_after) {
		this.padding_after = padding_after;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meeting_after == null) ? 0 : meeting_after.hashCode());
		result = prime * result + ((meeting_before == null) ? 0 : meeting_before.hashCode());
		result = prime * result + ((not_available == null) ? 0 : not_available.hashCode());
		result = prime * result + ((padding_after == null) ? 0 : padding_after.hashCode());
		result = prime * result + ((padding_before == null) ? 0 : padding_before.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
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
		Slot other = (Slot) obj;
		if (meeting_after == null) {
			if (other.meeting_after != null)
				return false;
		} else if (!meeting_after.equals(other.meeting_after))
			return false;
		if (meeting_before == null) {
			if (other.meeting_before != null)
				return false;
		} else if (!meeting_before.equals(other.meeting_before))
			return false;
		if (not_available == null) {
			if (other.not_available != null)
				return false;
		} else if (!not_available.equals(other.not_available))
			return false;
		if (padding_after == null) {
			if (other.padding_after != null)
				return false;
		} else if (!padding_after.equals(other.padding_after))
			return false;
		if (padding_before == null) {
			if (other.padding_before != null)
				return false;
		} else if (!padding_before.equals(other.padding_before))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slot [not_available=" + not_available + ", start=" + start + ", rank=" + rank + ", padding_before=" + padding_before
				+ ", padding_after=" + padding_after + ", meeting_before=" + meeting_before + ", meeting_after=" + meeting_after + "]";
	}

}
