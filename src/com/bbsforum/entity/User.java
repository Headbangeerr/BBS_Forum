package com.bbsforum.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "bbs_forum")
public class User implements java.io.Serializable {

	// Fields

	private String mailAddress;
	private String username;
	private String password;
	private String sex;
	private Timestamp registerDate;
	private String photoUrl;
	private Integer userType;
	private Integer level;
	private Integer type;
	private String signature;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String mailAddress, String username, String password,
			String sex, String photoUrl) {
		this.mailAddress = mailAddress;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.photoUrl = photoUrl;
	}

	/** full constructor */
	public User(String mailAddress, String username, String password,
			String sex, Timestamp registerDate, String photoUrl,
			Integer userType, Integer level, Integer type, String signature) {
		this.mailAddress = mailAddress;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.registerDate = registerDate;
		this.photoUrl = photoUrl;
		this.userType = userType;
		this.level = level;
		this.type = type;
		this.signature = signature;
	}

	// Property accessors
	@Id
	@Column(name = "mail_address", unique = true, nullable = false, length = 20)
	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "sex", nullable = false, length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "register_date", length = 19)
	public Timestamp getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "photo_url", nullable = false)
	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Column(name = "user_type")
	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "signature")
	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}