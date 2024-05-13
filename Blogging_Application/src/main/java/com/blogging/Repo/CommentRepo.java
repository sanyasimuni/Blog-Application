package com.blogging.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
