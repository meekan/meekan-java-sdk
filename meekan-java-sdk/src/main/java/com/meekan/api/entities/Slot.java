package com.meekan.api.entities;

import java.util.List;

public class Slot implements MeekanEntity {

	private static final long serialVersionUID = 6055752784018268597L;

	private List<Long> not_available;
	private Long start;
	private Long rank;
	private Integer padding_before;
	private Integer padding_after;

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
				+ ", padding_after=" + padding_after + "]";
	}

}
