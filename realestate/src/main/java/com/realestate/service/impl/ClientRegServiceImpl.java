package com.realestate.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realestate.entities.ClientReg;
import com.realestate.mapper.ClientRegMapper;
import com.realestate.payload.ClientRegDto;
import com.realestate.repositories.ClientRegRepository;
import com.realestate.service.ClientRegService;

@Service
public class ClientRegServiceImpl implements ClientRegService {
	
	@Autowired
	private ClientRegRepository clientRegRepo;
	
	@Bean
	public static PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void saveClientReg(ClientRegDto clientRegDto) {
		
		ClientReg client = ClientRegMapper.mapToClientReg(clientRegDto);
		clientRegRepo.save(client);
	}

}
