package com.meekan.api.entities;

import java.util.List;

public class User implements MeekanEntity {

	private static final long serialVersionUID = 3067873694906452502L;

	private String primary_email;
	private String primary_account;
	private List<Account> accounts;
	private String user_id;
	private String name;
	private Preferences preferences;

	public String getPrimary_email() {
		return primary_email;
	}

	public void setPrimary_email(String primary_email) {
		this.primary_email = primary_email;
	}

	public String getPrimary_account() {
		return primary_account;
	}

	public void setPrimary_account(String primary_account) {
		this.primary_account = primary_account;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((preferences == null) ? 0 : preferences.hashCode());
		result = prime * result + ((primary_account == null) ? 0 : primary_account.hashCode());
		result = prime * result + ((primary_email == null) ? 0 : primary_email.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		User other = (User) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (preferences == null) {
			if (other.preferences != null)
				return false;
		} else if (!preferences.equals(other.preferences))
			return false;
		if (primary_account == null) {
			if (other.primary_account != null)
				return false;
		} else if (!primary_account.equals(other.primary_account))
			return false;
		if (primary_email == null) {
			if (other.primary_email != null)
				return false;
		} else if (!primary_email.equals(other.primary_email))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [primary_email=" + primary_email + ", primary_account=" + primary_account + ", accounts=" + accounts + ", user_id=" + user_id
				+ ", name=" + name + ", preferences=" + preferences + "]";
	}

}
