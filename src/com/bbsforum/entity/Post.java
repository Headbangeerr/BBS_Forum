package com.bbsforum.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "post", catalog = "bbs_forum")
public class Post implements java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String content;
	private User publisherMail;
	private Timestamp publishTime;
	private Childboard childboardId;
	private Integer pageView;

	// Constructors

	/** default constructor */
	public Post() {
	}



	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Column(name = "publish_time", nullable = false, length = 19)
	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	@ManyToOne(fetch=FetchType.EAGER )
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="childboard_id")
	public Childboard getChildboardId() {
		return childboardId;
	}
	public void setChildboardId(Childboard childboardId) {
		this.childboardId = childboardId;
	}



	@Column(name = "page_view", nullable = false)
	public Integer getPageView() {
		return this.pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

}