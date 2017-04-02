package com.bbsforum.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "post", catalog = "bbs_forum")
public class Post implements java.io.Serializable {

	// Fields

	private String id;//编号的生成规则是使用当时精确到毫秒的系统时间与发表用户的邮箱地址串接成的字符串。
	private String title;
	private String content;
	private String publisherMail;
	private String publisherUsername;
	private Integer publisherType;
	private Timestamp publishTime;
	private Integer childboardId;
	private String childboardName;
	private Integer pageView;//流浪两
	private Integer replyNumber;//回复数
	private String lastupdateUsermail;
	private String lastupdateUsername;
	private Integer lastupdateUsertype;

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** full constructor */
	public Post(String id, String title, String content, String publisherMail,
			String publisherUsername, Integer publisherType,
			Timestamp publishTime, Integer childboardId, String childboardName,
			Integer pageView, Integer replyNumber, String lastupdateUsermail,
			String lastupdateUsername, Integer lastupdateUsertype) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.publisherMail = publisherMail;
		this.publisherUsername = publisherUsername;
		this.publisherType = publisherType;
		this.publishTime = publishTime;
		this.childboardId = childboardId;
		this.childboardName = childboardName;
		this.pageView = pageView;
		this.replyNumber = replyNumber;
		this.lastupdateUsermail = lastupdateUsermail;
		this.lastupdateUsername = lastupdateUsername;
		this.lastupdateUsertype = lastupdateUsertype;
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

	@Column(name = "publisher_mail", nullable = false, length = 20)
	public String getPublisherMail() {
		return this.publisherMail;
	}

	public void setPublisherMail(String publisherMail) {
		this.publisherMail = publisherMail;
	}

	@Column(name = "publisher_username", nullable = false, length = 50)
	public String getPublisherUsername() {
		return this.publisherUsername;
	}

	public void setPublisherUsername(String publisherUsername) {
		this.publisherUsername = publisherUsername;
	}

	@Column(name = "publisher_type", nullable = false)
	public Integer getPublisherType() {
		return this.publisherType;
	}

	public void setPublisherType(Integer publisherType) {
		this.publisherType = publisherType;
	}

	@Column(name = "publish_time", nullable = false, length = 19)
	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "childboardId", nullable = false)
	public Integer getChildboardId() {
		return this.childboardId;
	}

	public void setChildboardId(Integer childboardId) {
		this.childboardId = childboardId;
	}

	@Column(name = "childboardName", nullable = false, length = 10)
	public String getChildboardName() {
		return this.childboardName;
	}

	public void setChildboardName(String childboardName) {
		this.childboardName = childboardName;
	}

	@Column(name = "page_view", nullable = false)
	public Integer getPageView() {
		return this.pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	@Column(name = "reply_number", nullable = false)
	public Integer getReplyNumber() {
		return this.replyNumber;
	}

	public void setReplyNumber(Integer replyNumber) {
		this.replyNumber = replyNumber;
	}

	@Column(name = "lastupdate_usermail", nullable = false, length = 20)
	public String getLastupdateUsermail() {
		return this.lastupdateUsermail;
	}

	public void setLastupdateUsermail(String lastupdateUsermail) {
		this.lastupdateUsermail = lastupdateUsermail;
	}

	@Column(name = "lastupdate_username", nullable = false, length = 50)
	public String getLastupdateUsername() {
		return this.lastupdateUsername;
	}

	public void setLastupdateUsername(String lastupdateUsername) {
		this.lastupdateUsername = lastupdateUsername;
	}

	@Column(name = "lastupdate_usertype", nullable = false)
	public Integer getLastupdateUsertype() {
		return this.lastupdateUsertype;
	}

	public void setLastupdateUsertype(Integer lastupdateUsertype) {
		this.lastupdateUsertype = lastupdateUsertype;
	}

}