package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.UserMaster;
import com.example.demo.models.UpdateUser;
import com.example.demo.models.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping(value = "user")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {

		UserMaster findByEmail = userService.findByEmail(user.getEmail());
		if (findByEmail != null) {
			return new ResponseEntity<>("Email already Registred", HttpStatus.BAD_REQUEST);
		}

		UserMaster userMaster = new UserMaster();

		BeanUtils.copyProperties(user, userMaster);

		userMaster.setPassword(encoder.encode(userMaster.getPassword()));
		userMaster.setEnabled(1);

		UserMaster saveUser = userService.saveUser(userMaster);

		return new ResponseEntity<>("Registration Successful, User Id : "+saveUser.getId(), HttpStatus.CREATED);

	}

	@PutMapping(value = "user")
	public ResponseEntity<String> updateUser(@Valid @RequestBody UpdateUser user) {

		Integer id = user.getId(); 
		
		UserMaster findUser = userService.findUser(id);
		
		if(findUser == null) {

			return new ResponseEntity<>("User Id doesn't exist", HttpStatus.BAD_REQUEST);
		}

//		UserMaster userMaster = userService.findByEmail(user.getEmail());

		BeanUtils.copyProperties(user, findUser);

		findUser.setPassword(encoder.encode(findUser.getPassword()));

		userService.saveUser(findUser);

		return new ResponseEntity<>("User Updated", HttpStatus.CREATED);

	}

	@DeleteMapping(value = "user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {

		userService.deleteUser(id);

		return new ResponseEntity<>("User Deleted", HttpStatus.NO_CONTENT);

	}

	@GetMapping(value = "users")
	public ResponseEntity<List<UserMaster>> getAllUsers() {

		List<UserMaster> allUsers = userService.getAllUsers();

		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping(value = "user/{id}")
	public ResponseEntity<UserMaster> getUser(@PathVariable("id") Integer id) {

		UserMaster findUser = userService.findUser(id);

		return new ResponseEntity<>(findUser, HttpStatus.OK);
	}

}
