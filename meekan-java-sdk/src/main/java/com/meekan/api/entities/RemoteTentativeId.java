package com.meekan.api.entities;

public class RemoteTentativeId implements MeekanEntity {

	private static final long serialVersionUID = 5253299089942387874L;

	private String remote_id;
	private Long start;
	private Integer duration;

	public String getRemote_id() {
		return remote_id;
	}

	public Long getStart() {
		return start;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setRemote_id(String remote_id) {
		this.remote_id = remote_id;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((remote_id == null) ? 0 : remote_id.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RemoteTentativeId))
			return false;
		RemoteTentativeId other = (RemoteTentativeId) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (remote_id == null) {
			if (other.remote_id != null)
				return false;
		} else if (!remote_id.equals(other.remote_id))
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
		return "RemoteTentativeId [remote_id=" + remote_id + ", start=" + start + ", duration=" + duration + "]";
	}

}
