package com.blogging.Service;

import java.util.List;

import com.blogging.Payload.PostResponse;
import com.blogging.Payload.postDTO;

public interface PostService {
	
	//create post
  public 	postDTO  createPost(postDTO postDTO,int userId,int categoryId);

  //update
  public postDTO  UpdatePost(postDTO postdto,int postId);
  
  //delete
  
  public void deletePost(int postId);
  
  //getallPost
  
  public PostResponse getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir); 
  
  //getPost bypostId
  
  public postDTO getPostbyId(int postId);
  
  //get post bycategory
  
  public List<postDTO>getPostByCategory(int categoryId);
  
  //get postby User
  
  public List<postDTO>getPostByUser(int userId);
  
  //search post
  
  public List<postDTO>searchPost(String keyword);


  
  
  
  
  
  
  
  
  
  
  
  
}
