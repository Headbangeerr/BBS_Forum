package com.bbsforum.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reply", catalog = "bbs_forum")
public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private User senderMail;
	private Timestamp sendtime;
	private String content;
	private String postId;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply( String content, String postId) {
		this.content = content;
		this.postId = postId;
	}

	/** full constructor */
	public Reply(Timestamp sendtime, String content,
			String postId) {
		this.sendtime = sendtime;
		this.content = content;
		this.postId = postId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="sender_mail")
	public User getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(User senderMail) {
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