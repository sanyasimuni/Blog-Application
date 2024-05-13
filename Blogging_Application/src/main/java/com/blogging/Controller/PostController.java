package com.blogging.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.Config.AppConstants;
import com.blogging.Payload.ApiResponse;
import com.blogging.Payload.PostResponse;
import com.blogging.Payload.postDTO;
import com.blogging.Service.PostService;
import com.blogging.Service.fileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private fileService  fileService;
	
	
	//create Post
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<postDTO>Create_Post(@RequestBody postDTO postdto,@PathVariable int userId,@PathVariable int categoryId){
		                  
		                    postDTO dto=this.postService.createPost(postdto, userId, categoryId);
		                    
		                    return new ResponseEntity<postDTO>(dto,HttpStatus.CREATED);
		
	}

	
	//getCategory  all id in post
	
	@GetMapping("/categoty/{categoryId}/posts")
	public ResponseEntity<List<postDTO>>GetPostByCategory(@PathVariable int categoryId){
		
		                  List<postDTO>list= this.postService.getPostByCategory(categoryId);
		                  return new ResponseEntity<List<postDTO>>(list,HttpStatus.OK);
		
	}
	
	//get User All the post
	

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<postDTO>>GetPostByUser(@PathVariable int userId){
		
		                  List<postDTO>list2= this.postService.getPostByUser(userId);
		                  return new ResponseEntity<List<postDTO>>(list2,HttpStatus.OK);
		
	}
	
	
	//All Post GET
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>GetAllPost(
			@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)int pageNumber,
			@RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)int pageSize,
			@RequestParam (value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam (value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
			
			
			)
	{
		
		          PostResponse postdto=this.postService.getAllPost(pageNumber, pageSize, sortBy ,sortDir);
		          
		          return new ResponseEntity<PostResponse>(postdto,HttpStatus.OK);
		
	}
	
	
	//GET single Post By ID
	@GetMapping("/posts/{postId}")
	public ResponseEntity<postDTO>GetSinglePost(@PathVariable int postId){
		
		          postDTO po= this.postService.getPostbyId(postId);
		          
		          return new ResponseEntity<postDTO>(po,HttpStatus.OK);
		
	}
	
	
	//Delete post
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse>DeletePost(@PathVariable int postId){
		
		                    this.postService.deletePost(postId);
		                    return new ResponseEntity<ApiResponse>(new ApiResponse("Post Delete Success!!!",true),HttpStatus.OK);
		
	}
	
	
	//update Post
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<postDTO>UpdatePost(@RequestBody postDTO postDTO,@PathVariable int postId){
		                 postDTO pp= this.postService.UpdatePost(postDTO, postId);
		                 return new ResponseEntity<postDTO>(pp,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<postDTO>>SearchPostByTitle(@PathVariable("keywords") String keywords){
		
		                    List<postDTO>listpost=this.postService.searchPost(keywords);
		                    return new ResponseEntity<List<postDTO>>(listpost,HttpStatus.OK);
		
	}
	
	//image upload
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<postDTO>UploadPostImage(
			@RequestParam("image")MultipartFile image,@PathVariable int postId) throws IOException{
		
		postDTO postdto=this.postService.getPostbyId(postId);
		     String filename=this.fileService.UploadImage(path, image);
		     postdto.setImageName(filename);
		     postDTO update=this.postService.UpdatePost(postdto, postId);
		     
		     return new ResponseEntity<postDTO>(update,HttpStatus.OK);
	}
	
	@GetMapping(value = "/post/image/{imageName}")
	public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response ) throws IOException {
		
		InputStream resource= this.fileService.getResource(path, imageName);
		       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		       org.springframework.util.StreamUtils.copy(resource,response.getOutputStream());
		
		
	}
	
}
