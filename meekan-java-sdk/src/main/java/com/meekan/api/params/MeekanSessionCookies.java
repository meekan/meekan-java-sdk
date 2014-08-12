package com.meekan.api.params;

/**
 * This class represent a Meekan session cookie.
 * Use it when you got a cookie from authenticate Meekan from web (f.e - Google)
 * 
 * @author idog
 * 
 */
public class MeekanSessionCookies {

	private String sessionName;
	private String session;

	public MeekanSessionCookies(String sessionName, String session) {
		this.sessionName = sessionName;
		this.session = session;
	}

	public String getSessionName() {
		return sessionName;
	}

	public String getSession() {
		return session;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		result = prime * result + ((sessionName == null) ? 0 : sessionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MeekanSessionCookies))
			return false;
		MeekanSessionCookies other = (MeekanSessionCookies) obj;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		if (sessionName == null) {
			if (other.sessionName != null)
				return false;
		} else if (!sessionName.equals(other.sessionName))
			return false;
		return true;
	}

}
