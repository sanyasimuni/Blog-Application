package com.blogging.Service;

import java.util.List;

import com.blogging.Payload.UserDto;

public interface UserService {
	//create
 UserDto createUser(UserDto userDto);
 //update
 UserDto updateUser(UserDto userDto,int userid);
 
 //getsingleByid
      UserDto   getuserByid(int userid);
  //listofvalueget
      List<UserDto>getallUser();
      
      //deletesingleByid
      void deleteUser(int userid);

}
