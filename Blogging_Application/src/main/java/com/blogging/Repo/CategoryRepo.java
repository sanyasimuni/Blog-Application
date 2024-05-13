package com.blogging.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.Entity.Category;

public interface CategoryRepo  extends JpaRepository<Category, Integer>{ 

}
