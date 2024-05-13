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
import com.blogging.Payload.UserDto;
import com.blogging.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	//Create/post
	@PostMapping("/")
	public ResponseEntity<UserDto>saveUserDetails(@Valid @RequestBody UserDto userDto){
		
		              UserDto userdto=service.createUser(userDto);
		              return new ResponseEntity<>(userdto,HttpStatus.CREATED);
		
	}
	
	
	//Update/Put
	
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto>UpdateUser(@RequestBody UserDto userDto,@PathVariable int userid){
	
		
		 UserDto dto=this.service.updateUser(userDto, userid);
		 return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
		
	}
	
	
	
	//SingleByid
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto>SingleIdGet(@PathVariable int userid){
		   UserDto userd=this.service.getuserByid(userid);
		   return new ResponseEntity<UserDto>(userd,HttpStatus.OK);
		
	}
	
	
	//List of id get
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>ListofUser(){
		 
		           List<UserDto>userdto=this.service.getallUser();
		           return new ResponseEntity<List<UserDto>>(userdto,HttpStatus.OK);
		
	}
	
	
	
	
	//Delete User Id
	@DeleteMapping("/{userid}")
	public ResponseEntity<ApiResponse>DeleteUser(@PathVariable int userid){
		      this.service.deleteUser(userid);
		      return new ResponseEntity<>(new ApiResponse("User Delete Success!!",true),HttpStatus.OK);
		
	}

}
