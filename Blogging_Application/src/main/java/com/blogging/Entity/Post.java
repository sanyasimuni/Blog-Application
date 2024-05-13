package com.blogging.Entity;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Post_Table")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int postId;
	@Column(name="Title",length = 10000,nullable = false)
	private String title;
	@Column(name="Content",length = 100000000,nullable = false)
	private String content;
	private String imageName;
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	@OneToMany(mappedBy = "post",cascade =  CascadeType.ALL)
	private Set<Comment>comments=new HashSet<>();

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(String title, String content, String imageName, Date addDate, Category category, User user) {
		super();
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addDate = addDate;
		this.category = category;
		this.user = user;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
