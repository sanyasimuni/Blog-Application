package com.blogging.Payload;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogging.Entity.Comment;

public class postDTO {
	
	
	private int postId;
	
	private String title;
	private String content;
	private String imageName;
	private Date addDate;
	private CategoryDTO category;
	private UserDto user;
	private Set<CommentDTO>comments=new HashSet<>();
	public postDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
	
	public postDTO(int postId, String title, String content, String imageName, Date addDate, CategoryDTO category,
			UserDto user) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addDate = addDate;
		this.category = category;
		this.user = user;
	}
	
	

}
