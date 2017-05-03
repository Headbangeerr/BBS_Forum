package com.bbsforum.entity;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Friends entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "friends", catalog = "bbs_forum")
public class Friends implements java.io.Serializable {

	// Fields

	private FriendsId id;
	private Timestamp addDate;

	// Constructors

	/** default constructor */
	public Friends() {
	}

	/** minimal constructor */
	public Friends(FriendsId id) {
		this.id = id;
	}

	/** full constructor */
	public Friends(FriendsId id, Timestamp addDate) {
		this.id = id;
		this.addDate = addDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userMail", column = @Column(name = "user_mail", nullable = false, length = 20)),
			@AttributeOverride(name = "friendMail", column = @Column(name = "friend_mail", nullable = false, length = 20)) })
	public FriendsId getId() {
		return this.id;
	}

	public void setId(FriendsId id) {
		this.id = id;
	}

	@Column(name = "add_date", length = 19)
	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

}