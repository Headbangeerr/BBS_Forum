package com.bbsforum.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FriendsId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class FriendsId implements java.io.Serializable {

	// Fields

	private String userMail;
	private String friendMail;

	// Constructors

	/** default constructor */
	public FriendsId() {
	}

	/** full constructor */
	public FriendsId(String userMail, String friendMail) {
		this.userMail = userMail;
		this.friendMail = friendMail;
	}

	// Property accessors

	@Column(name = "user_mail", nullable = false, length = 20)
	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	@Column(name = "friend_mail", nullable = false, length = 20)
	public String getFriendMail() {
		return this.friendMail;
	}

	public void setFriendMail(String friendMail) {
		this.friendMail = friendMail;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FriendsId))
			return false;
		FriendsId castOther = (FriendsId) other;

		return ((this.getUserMail() == castOther.getUserMail()) || (this
				.getUserMail() != null && castOther.getUserMail() != null && this
				.getUserMail().equals(castOther.getUserMail())))
				&& ((this.getFriendMail() == castOther.getFriendMail()) || (this
						.getFriendMail() != null
						&& castOther.getFriendMail() != null && this
						.getFriendMail().equals(castOther.getFriendMail())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserMail() == null ? 0 : this.getUserMail().hashCode());
		result = 37
				* result
				+ (getFriendMail() == null ? 0 : this.getFriendMail()
						.hashCode());
		return result;
	}

}