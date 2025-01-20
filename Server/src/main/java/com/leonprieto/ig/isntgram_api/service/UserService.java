package com.leonprieto.ig.isntgram_api.service;

import com.leonprieto.ig.isntgram_api.model.Users;
import com.leonprieto.ig.isntgram_api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Users registerUser(Users user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public boolean validateLogin(Users user) {
    // Fetch password.
    final Optional<Users> userName = userRepository.findByUsername(user.getUsername());
    if (userName.isEmpty()) {
      return false;
    }
    final String password = userName.get().getPassword();
    return passwordEncoder.matches(user.getPassword(), password);
  }

}
