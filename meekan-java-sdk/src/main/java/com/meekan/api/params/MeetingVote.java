package com.meekan.api.params;

public enum MeetingVote {
	NOT_YET(0),
	CUSTOM(1),
	ALWAYS(2),
	WHEN_AVAILABLE(3),
	NO(4),
	MAYBE(5);

	public final int voteValue;

	MeetingVote(int voteValue) {
		this.voteValue = voteValue;
	}

}
