package com.example.simpleecommerceapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simpleecommerceapp.entity.User;
import com.example.simpleecommerceapp.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	public User getUserById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
	}

	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	public User createUser(User user) {
		userRepo.save(user);
		return user;
	}
	
	public User updateUser(User user) {
		userRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("User with id " + user.getId() + " not found"));
		userRepo.save(user);
		return user;
	}
	
	public void deleteUser(Long id) {
		userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
		userRepo.deleteById(id);
	}
	
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public boolean verifyCredentials(String email, String password) {
		User User = userRepo.findByEmail(email);
		if (User.getPassword().equals(password)) {
			return true;
		}
		
		return false;
	}
	
}
