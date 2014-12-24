package com.meekan.api.entities;

public class Location implements MeekanEntity {

	private static final long serialVersionUID = 6123206998833420945L;

	private String address;
	private String latlon;

	public Location() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatlon() {
		return latlon;
	}

	public void setLatlon(String latlon) {
		this.latlon = latlon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((latlon == null) ? 0 : latlon.hashCode());
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
		Location other = (Location) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (latlon == null) {
			if (other.latlon != null)
				return false;
		} else if (!latlon.equals(other.latlon))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [address=" + address + ", latlon=" + latlon + "]";
	}

}
