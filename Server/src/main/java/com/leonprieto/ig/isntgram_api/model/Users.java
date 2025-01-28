package com.leonprieto.ig.isntgram_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Users {

  @Id
  private String id;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  @JoinColumn(nullable = false)
  @OneToOne(mappedBy = "user")
  private UserProfile profile;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserProfile getProfile() {
    return profile;
  }

  public void setProfile(UserProfile profile) {
    this.profile = profile;
  }
}
