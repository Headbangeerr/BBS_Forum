package com.bbsforum.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message", catalog = "bbs_forum")
public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private User publisherMail;
	private User reciverMail;
	private String content;
	private Timestamp publishDate;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message( String content) {
	
		this.content = content;
	}

	/** full constructor */
	public Message( String content,
			Timestamp publishDate) {
		
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

	

	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="publisher_mail")
	public User getPublisherMail() {
		return publisherMail;
	}

	public void setPublisherMail(User publisherMail) {
		this.publisherMail = publisherMail;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="receiver_mail")
	public User getReciverMail() {
		return reciverMail;
	}

	public void setReciverMail(User reciverMail) {
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