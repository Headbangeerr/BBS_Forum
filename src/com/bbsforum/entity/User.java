package com.bbsforum.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	private Integer type;
	private Integer level;
	private String signature;
	private Set<Post> posts=new HashSet<Post>();
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String mailAddress, String username, String password,
			String sex, Timestamp registerDate) {
		this.mailAddress = mailAddress;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.registerDate = registerDate;
	}

	/** full constructor */
	public User(String mailAddress, String username, String password,
			String sex, Timestamp registerDate, String photoUrl,
			Integer type, Integer level, String signature) {
		this.mailAddress = mailAddress;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.registerDate = registerDate;
		this.photoUrl = photoUrl;
		this.type = type;
		this.level = level;
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
	
	
	@OneToMany(mappedBy="publisherMail")
	@Cascade(value={CascadeType.DELETE})
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
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

	@Column(name = "register_date", nullable = false, length = 19)
	public Timestamp getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "photo_url", length = 40)
	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "signature")
	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}