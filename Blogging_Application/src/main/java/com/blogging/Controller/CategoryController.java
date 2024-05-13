package com.blogging.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.Payload.ApiResponse;
import com.blogging.Payload.CategoryDTO;
import com.blogging.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	
	//create 
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> CreateCategory(@Valid   @RequestBody CategoryDTO categoryDTO){
		                      
		CategoryDTO dto=this.service.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(dto,HttpStatus.CREATED);
		
		
	}
	
	
	//update 
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO>UpdateCatrgoty(@RequestBody CategoryDTO categoryDTO,@PathVariable int categoryId){
		
		                CategoryDTO categoty=this.service.UpdateCategory(categoryDTO, categoryId);
		                
		                return new ResponseEntity<CategoryDTO>(categoty,HttpStatus.OK);
		
	}
	
	//delete
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse>DeleteCategory(@PathVariable int categoryId){
		            this.service.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Delete SuccessFully!!",true), HttpStatus.OK);
		
	}
	
	
	//getSingle
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO>getSingleCategory(@PathVariable int categoryId){
		        CategoryDTO dd=this.service.getSingleCategory(categoryId);
		
		        return new ResponseEntity<CategoryDTO>(dd,HttpStatus.OK);
	}
	
	
	
	//getLIst
	@GetMapping("/")
	public ResponseEntity <List<CategoryDTO>>getAllCategory(){
		        List<CategoryDTO> ddto=this.service.getListOfCategory();
		
		        return new ResponseEntity<List<CategoryDTO>>(ddto,HttpStatus.OK);
	}
	
	

}

