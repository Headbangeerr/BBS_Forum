package com.bbsforum.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.type.CalendarType;

/**
 * Childboard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "childboard", catalog = "bbs_forum")
public class Childboard implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Board parentBoard;
	private Set<Post> posts=new HashSet<Post>();

	// Constructors

	/** default constructor */
	public Childboard(){
	}

	/** full constructor */
	public Childboard(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="childboardId")
	@Cascade(value={CascadeType.DELETE})
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="parent_board")//Íâ¼ü×Ö¶ÎÃû
	public Board getParentBoard() {
		return parentBoard;
	}

	public void setParentBoard(Board parentBoard) {
		this.parentBoard = parentBoard;
	}

	

}