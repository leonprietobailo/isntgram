package com.leonprieto.ig.isntgram_api.service;

import com.leonprieto.ig.isntgram_api.model.Profile;
import com.leonprieto.ig.isntgram_api.model.User;
import com.leonprieto.ig.isntgram_api.repository.IProfileRepository;
import com.leonprieto.ig.isntgram_api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
// TODO: Rename to Auth Service?
@Service
public class UserService {

  private final IUserRepository userRepository;

  private final IProfileRepository profileRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(IUserRepository userRepository, IProfileRepository profileRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.profileRepository = profileRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User registerUser(User user) {
    // Create profile
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    // Generate and save associated entry for profile. TODO: Find if there is a better approach for this.
    final Profile userProfile = new Profile();
    userProfile.setUser(user);
    userProfile.setPosts(0);
    userProfile.setFollowers(0);
    userProfile.setFollowing(0);
    profileRepository.save(userProfile);
    // Link with profile and save.
    user.setProfile(userProfile);
    return userRepository.save(user);
  }

  public boolean validateLogin(User user) {
    // Fetch password.
    final Optional<User> userName = userRepository.findById(user.getId());
    if (userName.isEmpty()) {
      return false;
    }
    final String password = userName.get().getPassword();
    return passwordEncoder.matches(user.getPassword(), password);
  }

}
