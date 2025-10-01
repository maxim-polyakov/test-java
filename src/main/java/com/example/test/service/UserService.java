package com.example.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.test.entity.User;
import com.example.test.repo.UserRepo;

@Service
public class UserService {

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepo userRepo;

	public User getUserById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
	}

	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
    public User createUser(User user) {
        // Кодируем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return false;
        }
        // Сравниваем введенный пароль с хешем из БД
        return passwordEncoder.matches(password, user.getPassword());
    }
	
}
