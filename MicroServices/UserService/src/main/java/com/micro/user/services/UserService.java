package com.micro.user.services;

import java.util.List;

import com.micro.user.entities.User;

public interface UserService {
//	User operations:
	
//	Create:
	User saveUser(User user);
	
//	Get Users:
	List<User> getAllUsers();

//	Get Single User:
	User getUser(String userId);
	
//	Update User:
	User updateUser(String userId, User user);
	
//	Delete User:
	void deleteUser(String userId);
}
