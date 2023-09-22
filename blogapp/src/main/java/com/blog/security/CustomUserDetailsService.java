package com.blog.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;

	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		com.blog.entities.User user = this.userRepo.findByEmail(email);
		
		if(user !=null) {
			User authenticatedUser = new User(user.getEmail(), user.getPassword(), user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
			return authenticatedUser;
		} else {
			throw new UsernameNotFoundException("Invalid username and password");
		}
		
		
		
		
	}

}
