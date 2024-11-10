package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.UserEntity;
import com.main.service.UserService;

@RestController
@RequestMapping("/user")
public class HomeController {
	
	@Autowired
	private UserService service;
	@GetMapping
	public List<UserEntity> getuser(){
		System.out.println("getyting users");
		return this.service.getuser();
	}

}
