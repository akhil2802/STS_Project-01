package com.servers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servers.model.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
	
//	Find Server by IP Address:
	Server findByIpAddress(String ipAddress);
}
