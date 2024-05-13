package com.blogging.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
