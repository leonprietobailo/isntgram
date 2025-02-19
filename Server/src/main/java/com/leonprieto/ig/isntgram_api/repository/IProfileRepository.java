package com.leonprieto.ig.isntgram_api.repository;

import com.leonprieto.ig.isntgram_api.model.Profile;
import com.leonprieto.ig.isntgram_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProfileRepository extends JpaRepository<Profile, User> {
  Optional<Profile> findByUser(User user);

  List<Profile> findByUserIdContainingIgnoreCaseOrNameContainingIgnoreCase(String userId,
      String name);

}
