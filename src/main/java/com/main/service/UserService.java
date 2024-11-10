package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.main.entity.UserEntity;

@Service
public class UserService {
	private List<UserEntity> store = new ArrayList<UserEntity>();
	
	
	public UserService() {
		store.add(new UserEntity(UUID.randomUUID().toString(),"puja","puja123@gmail.com","puja@123"));
		store.add(new UserEntity(UUID.randomUUID().toString(),"Amarjeet","aj456@gmail.com","amar5823"));
		store.add(new UserEntity(UUID.randomUUID().toString(),"kanchan","adhikarikanchnan133@gmail.com","kanchan@3455"));
	}
	
	public List<UserEntity> getuser(){
		return this.store;
	}

}
