package com.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.payload.ClientRegDto;
import com.realestate.service.ClientRegService;

@RestController
@RequestMapping("/api/clientreg")
public class ClientRegController {

    @Autowired
    private ClientRegService clientRegService;

    @PostMapping("/create")
    public ResponseEntity<String> saveClientReg(@RequestBody ClientRegDto clientRegDto) {
        clientRegService.saveClientReg(clientRegDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

}
