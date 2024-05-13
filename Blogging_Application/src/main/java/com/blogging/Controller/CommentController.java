package com.blogging.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.Payload.ApiResponse;
import com.blogging.Payload.CommentDTO;
import com.blogging.Service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	
	//create
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO>CreateComment(@RequestBody CommentDTO commentDTO,@PathVariable
			int postId){
		          CommentDTO dto= this.commentService.createComment(commentDTO, postId);
		
		          return new ResponseEntity<CommentDTO>(dto,HttpStatus.CREATED);
	}

	//delete comment
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse>DeleteComment(@PathVariable ("commentId")int commentId){
		
		                   this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted!!",true),HttpStatus.OK);
		
	}
	}
	
	

