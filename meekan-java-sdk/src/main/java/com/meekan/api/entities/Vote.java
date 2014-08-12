package com.meekan.api.entities;

import java.util.List;

public class Vote implements MeekanEntity {

	private static final long serialVersionUID = -4827925282894605888L;

	private List<Long> preferred;
	private Integer resp_type;
	private String email;
	private Long updated;

	public List<Long> getPreferred() {
		return preferred;
	}

	public Integer getResp_type() {
		return resp_type;
	}

	public String getEmail() {
		return email;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setPreferred(List<Long> preferred) {
		this.preferred = preferred;
	}

	public void setResp_type(Integer resp_type) {
		this.resp_type = resp_type;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((preferred == null) ? 0 : preferred.hashCode());
		result = prime * result + ((resp_type == null) ? 0 : resp_type.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vote))
			return false;
		Vote other = (Vote) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (preferred == null) {
			if (other.preferred != null)
				return false;
		} else if (!preferred.equals(other.preferred))
			return false;
		if (resp_type == null) {
			if (other.resp_type != null)
				return false;
		} else if (!resp_type.equals(other.resp_type))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [preferred=" + preferred + ", resp_type=" + resp_type + ", email=" + email + ", updated=" + updated + "]";
	}

}
