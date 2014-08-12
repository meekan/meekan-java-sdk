package com.meekan.api.entities;

import java.util.List;

public class Slot implements MeekanEntity {

	private static final long serialVersionUID = 6055752784018268597L;

	private List<Long> not_available;
	private Long start;
	private Long rank;

	public List<Long> getNot_available() {
		return not_available;
	}

	public Long getStart() {
		return start;
	}

	public Long getRank() {
		return rank;
	}

	public void setNot_available(List<Long> not_available) {
		this.not_available = not_available;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((not_available == null) ? 0 : not_available.hashCode());
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
		if (!(obj instanceof Slot))
			return false;
		Slot other = (Slot) obj;
		if (not_available == null) {
			if (other.not_available != null)
				return false;
		} else if (!not_available.equals(other.not_available))
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
		return "Slot [not_available=" + not_available + ", start=" + start + ", rank=" + rank + "]";
	}

}
