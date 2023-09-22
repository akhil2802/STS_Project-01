package com.micro.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.user.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	
}
