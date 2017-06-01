package com.bbsforum.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Board entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "board", catalog = "bbs_forum")

public class Board implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String info;
	private Set<Childboard> childBoard=new HashSet<Childboard>();

	// Constructors

	/** default constructor */
	public Board() {
	}

	/** full constructor */
	public Board(String name, String info) {
		this.name = name;
		this.info = info;
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

	
	@OneToMany(mappedBy="parentBoard",fetch=FetchType.EAGER)
	@Cascade(value={CascadeType.DELETE})	
	public Set<Childboard>  getChildBoard() {
		return childBoard;
	}

	public void setChildBoard(Set childBoard) {
		this.childBoard = childBoard;
	}

	@Column(name = "name", nullable = false, length = 10)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "info", nullable = false, length = 100)

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}