package com.blogging.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.Entity.User;
import com.blogging.Exception.ResourceNotFoundException;
import com.blogging.Payload.UserDto;
import com.blogging.Repo.UserRepo;
import com.blogging.Service.UserService;
@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper  modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		       User user=this.dtoToUser(userDto);
		           User savedUser= this.repo.save(user);
		           
		           return this.userToDto(savedUser);

	}

	@Override
	public UserDto updateUser(UserDto userDto, int userid) {
		       User user=this.repo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","id",userid));
		          user.setName(userDto.getName());
		          user.setEmail(userDto.getEmail());
		          user.setPassword(userDto.getPassword());
		          user.setAbout(userDto.getAbout());
		          
		              User updateUser=this.repo.save(user);
		
		             UserDto userdto1= this.userToDto(updateUser);
		              
		return userdto1;
				
		
	}

	@Override
	public UserDto getuserByid(int userid) {
		
		 User user=this.repo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "id",userid));
         
		
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getallUser() {
		     List<User>users=this.repo.findAll();
		              List<UserDto>userDto=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		     
		
		
		return userDto;
	}

	@Override
	public void deleteUser(int userid) {
		this.repo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "id",userid));
        
		
		 this.repo.deleteById(userid);
		
	}
	
	public  User dtoToUser(UserDto userDto) {
		/*
		 * User user=new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */
		
		        User user=this.modelMapper.map(userDto,User.class);
		return user;
		
		
	}
	
public  UserDto userToDto(User user) {
		
	/*
	 * UserDto userDto=new UserDto(); userDto.setId(user.getId());
	 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
	 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
	 */
	
	 UserDto userDto=this.modelMapper.map(user,UserDto.class);
		return userDto;
		
		
	}

}

	
	
	
	
	
	
	
	
	
	


