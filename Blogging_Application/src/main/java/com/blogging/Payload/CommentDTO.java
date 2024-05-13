package com.blogging.Payload;

public class CommentDTO {
	private int Id;
	private String content;
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentDTO(int id, String content) {
		super();
		Id = id;
		this.content = content;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
