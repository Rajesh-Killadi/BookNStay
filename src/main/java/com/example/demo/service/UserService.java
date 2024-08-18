package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserMaster;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public UserMaster saveUser(UserMaster user) {
		
		return userRepo.save(user);
		
		
	}
	
	public boolean deleteUser(Integer id) {
		
		userRepo.deleteById(id);
		
		return true;
	}
	
	public List<UserMaster> getAllUsers(){
		
		return userRepo.findAll();
	}
	
	public UserMaster findByEmail(String email) {
		
		return userRepo.findByEmail(email).orElse(null);
	}
	
	public UserMaster findUser(Integer id) {
		
		return userRepo.findById(id).orElse(null);
	}
	

}
