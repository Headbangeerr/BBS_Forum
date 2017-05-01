package com.bbsforum.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message", catalog = "bbs_forum")
public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private String publisherMail;
	private String reciverMail;
	private String content;
	private Timestamp publishDate;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(String publisherMail, String reciverMail, String content) {
		this.publisherMail = publisherMail;
		this.reciverMail = reciverMail;
		this.content = content;
	}

	/** full constructor */
	public Message(String publisherMail, String reciverMail, String content,
			Timestamp publishDate) {
		this.publisherMail = publisherMail;
		this.reciverMail = reciverMail;
		this.content = content;
		this.publishDate = publishDate;
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

	@Column(name = "publisher_mail", nullable = false, length = 20)
	public String getPublisherMail() {
		return this.publisherMail;
	}

	public void setPublisherMail(String publisherMail) {
		this.publisherMail = publisherMail;
	}

	@Column(name = "reciver_mail", nullable = false, length = 20)
	public String getReciverMail() {
		return this.reciverMail;
	}

	public void setReciverMail(String reciverMail) {
		this.reciverMail = reciverMail;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "publish_date", length = 19)
	public Timestamp getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}

}