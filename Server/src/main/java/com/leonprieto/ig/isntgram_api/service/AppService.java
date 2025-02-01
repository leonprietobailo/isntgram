package com.leonprieto.ig.isntgram_api.service;

import com.leonprieto.ig.isntgram_api.model.UserProfile;
import com.leonprieto.ig.isntgram_api.model.Users;
import com.leonprieto.ig.isntgram_api.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService {

  @Autowired
  private IProfileRepository profileRepository;

  public Optional<UserProfile> getProfile(String userId) {
    final Users user = new Users();
    user.setId(userId);
    return profileRepository.findByUser(user);
  }

  public UserProfile updateProfile(UserProfile userProfile) {
    return profileRepository.save(userProfile);
  }
}
