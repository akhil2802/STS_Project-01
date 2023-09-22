package com.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeController {
	
	@RequestMapping("/ve")
	public String variable_expression(Model model) {
		
		Student student = new Student(1, "akhil", "mysuru", "akhil@gmail.com");
		model.addAttribute("student1", student);
		
		return "ve";
	}
	
	@RequestMapping("/se")
	public String selection_expression(Model model) {
		
		Student student = new Student(2, "anki", "bilikere", "anki@gmail.com");
		model.addAttribute("student2", student);
		
		return "se";
	}
	
	@RequestMapping("/me")
	public String message_expression() {
		return "me";
	}
	
	@RequestMapping("/le")
	public String link_expression(Model model) {
		model.addAttribute("id", 1);
		return "le";
	}
	
	@RequestMapping("/fe")
	public String fragment_expression() {
		return "fe";
	}
	
	@RequestMapping("/if-unless")
	public String ifUnless(Model model) {
		
		User u1 = new User("Akhil", "Admin", "ak@gmail.com");
		User u2 = new User("Jenny", "Admin", "jen@gmail.com");
		User u3 = new User("Monika", "User", "moni@gmail.com");
		
		List<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		
		model.addAttribute("users", users);
		
		return "if-unless";
	}
	
	@RequestMapping("/switch-case")
	public String switchCase(Model model) {
		User user = new User("Suman", "Admin", "suma@gmail.com");
		model.addAttribute("user", user);
		return "switch-case";
	}
	
}
