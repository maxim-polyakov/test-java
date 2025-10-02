package com.example.test.controller;

import com.example.test.entity.User;
import com.example.test.service.UserService;
import com.example.test.service.DuplicateCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Замена @Controller на @RestController
@RequestMapping("/api/users") // Опционально: общий префикс пути для API
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
}
    // Метод для получения пользователя по ID и возврата его в формате JSON
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user); // Возвращает пользователя со статусом 200 OK
    }

    // Метод для обновления пользователя
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id,user); // Предполагается, что createUser выполняет обновление
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Метод для добавления нового пользователя
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // Возвращает нового пользователя со статусом 201 Created
    }

    // Метод для удаления пользователя
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    try {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/check-duplicates")
    public ResponseEntity<?> checkForDuplicates(@RequestBody int[] numbers) {
        try {
            boolean hasDuplicates = userService.hasDuplicates(numbers);

            DuplicateCheckResponse response = new DuplicateCheckResponse(
                numbers,
                hasDuplicates,
                hasDuplicates ? "Массив содержит дубликаты" : "Дубликаты не найдены"
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при проверке дубликатов: " + e.getMessage());
        }
    }
}