package com.blogging.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogging.Entity.Category;
import com.blogging.Entity.Post;
import com.blogging.Entity.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	
	   List<Post>findAllByuser(User user);

	   List<Post>findAllByCategory(Category category);
	   
	  // List<Post>findBytitleContaning(String keyword);
	   
	   
	   @Query("select e from Post e where e.title like :key")
	   List<Post> findByTitleContaining(@Param("key")String title);

}
