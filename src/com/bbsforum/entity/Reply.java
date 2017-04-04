package com.bbsforum.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reply", catalog = "bbs_forum")
public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private String senderMail;
	private Timestamp sendtime;
	private String content;
	private String postId;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(String senderMail, String content, String postId) {
		this.senderMail = senderMail;
		this.content = content;
		this.postId = postId;
	}

	/** full constructor */
	public Reply(String senderMail, Timestamp sendtime, String content,
			String postId) {
		this.senderMail = senderMail;
		this.sendtime = sendtime;
		this.content = content;
		this.postId = postId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "sender_mail", nullable = false, length = 20)
	public String getSenderMail() {
		return this.senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	@Column(name = "sendtime", length = 19)
	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "postId", nullable = false, length = 50)
	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

}