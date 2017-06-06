package com.bbsforum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Safety entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "safety", catalog = "bbs_forum")

public class Safety implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mailAddress;
	private String safetyCode;

	// Constructors

	/** default constructor */
	public Safety() {
	}

	/** full constructor */
	public Safety(String mailAddress, String safetyCode) {
		this.mailAddress = mailAddress;
		this.safetyCode = safetyCode;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "Id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "mail_address", nullable = false, length = 16)

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Column(name = "safetyCode", nullable = false, length = 16)

	public String getSafetyCode() {
		return this.safetyCode;
	}

	public void setSafetyCode(String safetyCode) {
		this.safetyCode = safetyCode;
	}

}