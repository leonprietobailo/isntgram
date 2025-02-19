package com.leonprieto.ig.isntgram_api.service;

import com.leonprieto.ig.isntgram_api.model.Profile;
import com.leonprieto.ig.isntgram_api.model.User;
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

  public Optional<Profile> getProfile(String userId) {
    final User user = new User();
    user.setId(userId);
    return profileRepository.findByUser(user);
  }

  public Profile updateProfile(Profile userProfile) {
    return profileRepository.save(userProfile);
  }

  public List<Profile> searchProile(String userId) {
    return profileRepository.findByUserIdContainingIgnoreCaseOrNameContainingIgnoreCase(userId,
        userId);
  }
}
