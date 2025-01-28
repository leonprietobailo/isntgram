package com.leonprieto.ig.isntgram_api.repository;

import com.leonprieto.ig.isntgram_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, String> {
  Optional<Users> findById(String username);
}
