package com.blogging.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.Entity.Comment;
import com.blogging.Entity.Post;
import com.blogging.Exception.ResourceNotFoundException;
import com.blogging.Payload.CommentDTO;
import com.blogging.Repo.CommentRepo;
import com.blogging.Repo.PostRepo;
import com.blogging.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, int postId) {

        Comment comment=  this.modelmapper.map(commentDTO, Comment.class);
		       
		                    Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		
		      comment.setPost(post);
		     Comment savedcomment= this.commentRepo.save(comment);
		
		return this.modelmapper.map(savedcomment,CommentDTO.class);
	}

	@Override
	public void deleteComment(int commentId) {
		
	//delete
		
		  Comment commen = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId",commentId));
			
		         this.commentRepo.delete(commen);
		
	}

}
