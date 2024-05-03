package org.swagger.lab2_swagger.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.swagger.lab2_swagger.model.User;
import org.swagger.lab2_swagger.service.UserSecurityService;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserSecurityService userSecurityService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setRole("ROLE_TOURIST");
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setBookings(new ArrayList<>());
        userSecurityService.registerUser(user);
        return ResponseEntity.ok().build();
    }
}