package com.blogging.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Comment_Table")
public class Comment {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int Id;
	private String Content;
	
	@ManyToOne
	private Post post;

	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Comment(String content, Post post) {
		super();
		Content = content;
		this.post = post;
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}
	
	
	
	
	

}
