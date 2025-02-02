package com.leonprieto.ig.isntgram_api.repository;

import com.leonprieto.ig.isntgram_api.model.UserProfile;
import com.leonprieto.ig.isntgram_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProfileRepository extends JpaRepository<UserProfile, Users> {
  Optional<UserProfile> findByUser(Users user);

  List<UserProfile> findByUserIdContainingIgnoreCase(String userId);

}
