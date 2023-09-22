package com.realestate.mapper;

import com.realestate.entities.ClientReg;
import com.realestate.payload.ClientRegDto;
import com.realestate.service.impl.ClientRegServiceImpl;

public class ClientRegMapper {

	// map ClientReg entity to ClientRegDto:
	public static ClientRegDto mapToClientRegDto(ClientReg clientReg) {

		return ClientRegDto.builder().clientId(clientReg.getClientId()).clientName(clientReg.getClientName())
				.clientAddress(clientReg.getClientAddress()).phoneNumber(clientReg.getPhoneNumber())
				.email(clientReg.getEmail()).gender(clientReg.getGender()).username(clientReg.getUsername())
				.password(clientReg.getPassword()).build();

	}

	public static ClientReg mapToClientReg(ClientRegDto clientRegDto) {

		return ClientReg.builder().clientId(clientRegDto.getClientId()).clientName(clientRegDto.getClientName())
				.clientAddress(clientRegDto.getClientAddress()).phoneNumber(clientRegDto.getPhoneNumber())
				.email(clientRegDto.getEmail()).gender(clientRegDto.getGender()).username(clientRegDto.getUsername())
				.password(ClientRegServiceImpl.getPasswordEncoder().encode(clientRegDto.getPassword())).build();

	}
}
