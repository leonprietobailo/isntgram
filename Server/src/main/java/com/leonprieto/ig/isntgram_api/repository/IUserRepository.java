package com.leonprieto.ig.isntgram_api.repository;

import com.leonprieto.ig.isntgram_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
  Optional<User> findById(String username);
}
