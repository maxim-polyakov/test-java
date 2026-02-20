package com.example.test.service;

import java.util.List;

import java.util.HashSet;
import java.util.Set;

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
	
	public User updateUser(Long id,User user) {
		userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
		userRepo.save(user);
		return user;
	}
	
	public void deleteUser(Long id) {
		userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
		userRepo.deleteById(id);
	}

	public boolean hasDuplicates(int[] array) {
        if (array == null || array.length <= 1) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();

        for (int num : array) {
            if (seen.contains(num)) {
                return true; // Найден дубликат
            }
            seen.add(num);
        }

        return false; // Дубликатов не найдено
    }
	
}
