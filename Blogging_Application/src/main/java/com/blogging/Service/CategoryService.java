package com.blogging.Service;

import java.util.List;

import com.blogging.Payload.CategoryDTO;

public interface CategoryService {
	
	//create
	
	public CategoryDTO createCategory(CategoryDTO categoryDTO);
	//update
	
	public CategoryDTO UpdateCategory(CategoryDTO categotyDto,int categoryId);
	//delete
	
	public void deleteCategory(int categoryId );
	
	//getSingle
	
	public CategoryDTO getSingleCategory(int categoryId );
	
	//getListof
	
	public List<CategoryDTO>getListOfCategory();

}
 