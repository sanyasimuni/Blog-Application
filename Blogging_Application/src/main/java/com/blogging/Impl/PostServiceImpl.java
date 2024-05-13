package com.blogging.Impl;




import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogging.Entity.Category;
import com.blogging.Entity.Post;
import com.blogging.Entity.User;
import com.blogging.Exception.ResourceNotFoundException;
import com.blogging.Payload.PostResponse;
import com.blogging.Payload.postDTO;
import com.blogging.Repo.CategoryRepo;
import com.blogging.Repo.PostRepo;
import com.blogging.Repo.UserRepo;
import com.blogging.Service.PostService;

@Service
public class PostServiceImpl  implements PostService{
	
	@Autowired
	 private PostRepo postrepo;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	

	@Autowired
	private CategoryRepo categoryRepo;
	
	
	

	@Override
	public postDTO createPost(postDTO postDTO, int userId, int  categoryId) {
		
		       User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","UserId", userId));
		
		      Category category= this.categoryRepo.findById( categoryId).orElseThrow(()->new ResourceNotFoundException("user","UserId", categoryId));
				
		                     Post posts= this.modelMapper.map(postDTO,Post.class );
		                     
		                     
		                     posts.setImageName("default.png");
		                     
		                     //util package 
		                     posts.setAddDate(new Date());
		                     posts.setCategory(category);
		                     
		                     posts.setUser(user);
		                   //  posts.setAddDate(new Date());
		                     
		                    Post newPost= this.postrepo.save(posts);
		                    
		                    
		                    
		                   return this.modelMapper.map(newPost, postDTO.class);  
		                    
		
		
		
		
	}

	@Override
	public postDTO UpdatePost(postDTO postdto, int postId) {

		 Post post=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));
			
		   post.setTitle(postdto.getTitle());
		   post.setContent(postdto.getContent());
		   post.setImageName(postdto.getImageName());
		   
		   
		                 Post update= this.postrepo.save(post);
		                 
		                 
		  return this.modelMapper.map(update, postDTO.class);
	}

	@Override
	public void deletePost(int postId) {
		
		   Post post=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));
			
			             this.postrepo.delete(post);
		
		
	}



	@Override
	public postDTO getPostbyId(int postId) {
		
		     Post pos=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));
		
		
		
		return this.modelMapper.map(pos, postDTO.class);
	}

	@Override
	public List<postDTO> getPostByCategory(int categoryId) {
		
		
		  Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("user","UserId", categoryId));
			
           
		                List<Post>posts= this.postrepo.findAllByCategory(category);
		                   
		                        List<postDTO>post=posts.stream().map((cat-> this.modelMapper.map(cat, postDTO.class))).collect(Collectors.toList());
		
		return post;
	}

	@Override
	public List<postDTO> getPostByUser(int userId) {
		 
		  User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","UserId", userId));
			
          
          List<Post>posts= this.postrepo.findAllByuser(user);
             
                  List<postDTO>posr=posts.stream().map(cat-> this.modelMapper.map(cat, postDTO.class)).collect(Collectors.toList());
		
		
		return posr;
		
		
		
	}
	
	
	
	
	
	

	@Override
	public List<postDTO> searchPost(String keyword) {
		
		
		                     List<Post>posts= this.postrepo.findByTitleContaining("%"+keyword+"%");
		                    List<postDTO>postdto= posts.stream().map((post)->this.modelMapper.map(posts,postDTO.class)).collect(Collectors.toList());
		return postdto;
	}

	
	
	
	
	
	
	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir) {
		
		//sorting
		Sort sort=null;
	if(sortDir.equalsIgnoreCase("asc"))
	{
			sort=Sort.by(sortBy).ascending();
			
		}else{
			sort=Sort.by(sortBy).descending();
			
			
		}
		
//		
		
		//pagination
		       Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		                Page<Post>pagepost= this.postrepo.findAll(p);
		                List<Post>allpost=pagepost.getContent();
		                
		                
		                //list will be get
		                List<postDTO>postreturn=allpost.stream().map(cat-> this.modelMapper.map(cat, postDTO.class)).collect(Collectors.toList());
		                
		                //set 
		                PostResponse postResp=new PostResponse();
		                postResp.setContent(postreturn);
		                postResp.setPageNumber(pagepost.getNumber());
		                postResp.setPageSize(pagepost.getSize());
		                postResp.setTotalElements(pagepost.getTotalElements());
		                postResp.setTotalPages(pagepost.getTotalPages());
		                postResp.setLastPage(pagepost.isLast());
		                
		        		
		
		return postResp;
	}

}
