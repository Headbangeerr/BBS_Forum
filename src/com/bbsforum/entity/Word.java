package com.bbsforum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Word entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "word", catalog = "bbs_forum")
public class Word implements java.io.Serializable {

	// Fields

	private String word;

	// Constructors

	/** default constructor */
	public Word() {
	}

	/** full constructor */
	public Word(String word) {
		this.word = word;
	}

	// Property accessors
	@Id
	@Column(name = "word", nullable = false)
	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}