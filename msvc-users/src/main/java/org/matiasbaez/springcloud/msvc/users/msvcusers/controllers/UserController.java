package org.matiasbaez.springcloud.msvc.users.msvcusers.controllers;

import org.matiasbaez.springcloud.msvc.users.msvcusers.models.entity.User;
import org.matiasbaez.springcloud.msvc.users.msvcusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            return validateRequestBody(result);
        }

        if (!user.getEmail().isBlank() && service.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "Ya existe un usuario con ese correo electronico"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validateRequestBody(result);
        }

        Optional<User> optionalUser = service.getById(id);
        if (optionalUser.isPresent()) {
            User userObj = optionalUser.get();

            if (!user.getEmail().isBlank() && !user.getEmail().equalsIgnoreCase(userObj.getEmail()) && service.getByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("message", "Ya existe un usuario con ese correo electronico"));
            }

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

    private static ResponseEntity<Map<String, String>> validateRequestBody(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
