package com.micro.hotel.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffsController {
	
	public ResponseEntity<List<String>> getStaffs() {
		List<String> staffs = Arrays.asList("Anil", "John", "Sunil", "Mahesh", "Ganesh");
		return new ResponseEntity<>(staffs, HttpStatus.OK);
	}
}
