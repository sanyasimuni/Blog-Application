package com.blogging.Service;

import com.blogging.Payload.CommentDTO;

public interface CommentService {
	
	
	//create
	public CommentDTO createComment(CommentDTO commentDTO,int postId);
	
	//delete
	
	void deleteComment(int commentId);
	
	
	

}
