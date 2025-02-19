package com.leonprieto.ig.isntgram_api.controller;

import com.leonprieto.ig.isntgram_api.model.User;
import com.leonprieto.ig.isntgram_api.security.JwtTokenUtil;
import com.leonprieto.ig.isntgram_api.service.UserService;
import com.leonprieto.ig.isntgram_api.service.response.GenericApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @PostMapping("login")
  public ResponseEntity<GenericApiResponse> login(@RequestBody User user) {
    final boolean result = userService.validateLogin(user);
    if (result) {
      final String token = JwtTokenUtil.generateToken(user.getId());
      return ResponseEntity.ok(new GenericApiResponse(true, token));
    }
    return ResponseEntity.ok(new GenericApiResponse(false, "Login credentials are not correct."));
  }

  @PostMapping("register")
  public ResponseEntity<GenericApiResponse> register(@RequestBody User user) {
    user.setId(user.getId().toLowerCase());
    final User users = userService.registerUser(user);
    if (users != null) {
      final String token = JwtTokenUtil.generateToken(users.getId());
      return ResponseEntity.ok(new GenericApiResponse(true, token));
    }
    return ResponseEntity.ok(new GenericApiResponse(false, "User registration failed."));
  }
}
