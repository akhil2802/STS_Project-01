package com.servers.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.servers.enumeration.Status;
import com.servers.model.Server;
import com.servers.repository.ServerRepository;
import com.servers.service.ServerService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ServerServiceImpl implements ServerService {

	private final ServerRepository serverRepo;

//	CREATE:

	@Override
	public Server create(Server server) {
		log.info("Saving new server: {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepo.save(server);
	}

//	TODO:
	private String setServerImageUrl() {
		String[] imageNames = { "server1.png", "server2.png", "server3.png", "server4.png" };
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
	}

//	GET ALL:

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");
		return serverRepo.findAll(PageRequest.of(0, limit)).toList();
	}

//	GET ONE:

	@Override
	public Server get(Long id) {
		log.info("Fetching server by id: {}", id);
		return serverRepo.findById(id).get();
	}

//	UPDATE:

	@Override
	public Server update(Server server) {
		log.info("Updating server: {}", server.getName());
		return serverRepo.save(server);
	}

//	DELETE:

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by ID: {}", id);
		serverRepo.deleteById(id);
		return Boolean.TRUE;
	}

//	PING TO SERVER:

	@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("Pinging server IP: {}", ipAddress);
		Server server = serverRepo.findByIpAddress(ipAddress);
		InetAddress address = InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		serverRepo.save(server);
		return server;
	}

}
