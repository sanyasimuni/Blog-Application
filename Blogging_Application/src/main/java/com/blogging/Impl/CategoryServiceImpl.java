package com.blogging.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.Entity.Category;
import com.blogging.Exception.ResourceNotFoundException;
import com.blogging.Payload.CategoryDTO;
import com.blogging.Repo.CategoryRepo;
import com.blogging.Service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		
		                 Category category= this.mapper.map(categoryDTO, Category.class);
		                      Category category2= this.categoryRepo.save(category);
		
		
		return this.mapper.map(category2, CategoryDTO.class);
	}

	@Override
	public CategoryDTO UpdateCategory(CategoryDTO categotyDto, int categoryId) {
		
		                         Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "CategoryID", categoryId));
		                           
		                         category.setTitle(categotyDto.getTitle());
		                         category.setDescription(categotyDto.getDescription());
		                         
		                         Category up= this.categoryRepo.save(category);
		
		return this.mapper.map(up, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		
		  Category delet=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "CategoryID", categoryId));
            
		       this.categoryRepo.delete(delet);
		
		
	}

	@Override
	public CategoryDTO getSingleCategory(int categoryId) {
		
		 Category singleid=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "CategoryID", categoryId));
         
		     
		
		return this.mapper.map(singleid, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getListOfCategory() {
		
		      List<Category>category= this.categoryRepo.findAll();
		                            List<CategoryDTO>categoryes=  category.stream().map((cat)->this.mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		
		
		
		return categoryes;
	}

}
