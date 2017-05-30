package com.bbsforum.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "bbs_forum")
public class News implements java.io.Serializable {

	// Fields

	private String id;
	private User SenderMail;
	private User ReceiverMail;
	private Timestamp sendDate;
	private String content;
	private Integer type;
	private Integer state;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(String id, User userBySenderMail, User userByReceiverMail,
			Timestamp sendDate, String content) {
		this.id = id;
		this.SenderMail = userBySenderMail;
		this.ReceiverMail = userByReceiverMail;
		this.sendDate = sendDate;
		this.content = content;
	}

	/** full constructor */
	public News(String id, User userBySenderMail, User userByReceiverMail,
			Timestamp sendDate, String content, Integer type, Integer state) {
		this.id = id;
		this.SenderMail = userBySenderMail;
		this.ReceiverMail = userByReceiverMail;
		this.sendDate = sendDate;
		this.content = content;
		this.type = type;
		this.state = state;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 40)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_mail", nullable = false)
	public User getSenderMail() {
		return this.SenderMail;
	}

	public void setSenderMail(User userBySenderMail) {
		this.SenderMail = userBySenderMail;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver_mail", nullable = false)
	public User getReceiverMail() {
		return this.ReceiverMail;
	}

	public void setReceiverMail(User userByReceiverMail) {
		this.ReceiverMail = userByReceiverMail;
	}

	@Column(name = "send_date", nullable = false, length = 19)
	public Timestamp getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}