package com.example.test.controller;

import com.example.test.entity.User;
import com.example.test.service.UserService;
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
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user); // Предполагается, что createUser выполняет обновление
        return ResponseEntity.ok(updatedUser); // Возвращает обновленного пользователя
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
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Возвращает статус 204 No Content при успешном удалении
    }
}