package org.matiasbaez.springcloud.msvc.users.msvcusers.controllers;

import org.matiasbaez.springcloud.msvc.users.msvcusers.models.entity.User;
import org.matiasbaez.springcloud.msvc.users.msvcusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<User> optionalUser = service.getById(id);
        if (optionalUser.isPresent()) return ResponseEntity.ok(optionalUser.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        Optional<User> optionalUser = service.getById(id);
        if (optionalUser.isPresent()) {
            User userObj = optionalUser.get();
            userObj.setName(user.getName());
            userObj.setEmail(user.getEmail());
            userObj.setPassword(user.getPassword());

            return ResponseEntity.status(HttpStatus.OK).body(service.save(userObj));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> optionalUser = service.getById(id);
        if (optionalUser.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
