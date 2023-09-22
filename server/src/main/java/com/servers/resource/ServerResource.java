package com.servers.resource;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servers.enumeration.Status;
import com.servers.model.Response;
import com.servers.model.Server;
import com.servers.service.ServerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

	private final ServerService serverService;

	@GetMapping("/list")
	public ResponseEntity<Response> getServers() {
		return ResponseEntity
				.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("servers", serverService.list(30)))
						.message("servers retrieved").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
	}

	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable String ipAddress) throws IOException {
		Server server = serverService.ping(ipAddress);
		return ResponseEntity.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", server))
				.message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed").status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value()).build());
	}
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", serverService.create(server)))
				.message("server created").status(HttpStatus.CREATED)
				.statusCode(HttpStatus.CREATED.value()).build());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable Long id) {
		return ResponseEntity.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", serverService.get(id))) 
				.message("server retrieved").status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value()).build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable Long id) {
		return ResponseEntity.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("deleted", serverService.delete(id))) 
				.message("server deleted").status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value()).build());
	}
	
	@GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable String fileName) throws IOException {
	    ClassPathResource imageResource = new ClassPathResource("static/images/" + fileName); 
	    InputStream inputStream = imageResource.getInputStream();
	    return StreamUtils.copyToByteArray(inputStream);
	}
}
