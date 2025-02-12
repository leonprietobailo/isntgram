package com.leonprieto.ig.isntgram_api.service;

import com.leonprieto.ig.isntgram_api.model.UserProfile;
import com.leonprieto.ig.isntgram_api.model.Users;
import com.leonprieto.ig.isntgram_api.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppService {

  private final IProfileRepository profileRepository;

  @Autowired
  public AppService(IProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  public Optional<UserProfile> getProfile(String userId) {
    final Users user = new Users();
    user.setId(userId);
    return profileRepository.findByUser(user);
  }

  public UserProfile updateProfile(UserProfile userProfile) {
    return profileRepository.save(userProfile);
  }

  public List<UserProfile> searchProile(String userId) {
    return profileRepository.findByUserIdContainingIgnoreCaseOrNameContainingIgnoreCase(userId,
        userId);
  }
}
