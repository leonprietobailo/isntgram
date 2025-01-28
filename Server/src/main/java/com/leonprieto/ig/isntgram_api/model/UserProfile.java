package com.leonprieto.ig.isntgram_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity(name = "user_profile")
public class UserProfile {

  @Id
  private String userId;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  @MapsId
  @JsonIgnore
  private Users user;

  @Column
  private String name;

  @Column
  private String description;

  @Column(nullable = false)
  private Integer posts;

  @Column(nullable = false)
  private Integer followers;

  @Column(nullable = false)
  private Integer following;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPosts() {
    return posts;
  }

  public void setPosts(Integer posts) {
    this.posts = posts;
  }

  public Integer getFollowers() {
    return followers;
  }

  public void setFollowers(Integer followers) {
    this.followers = followers;
  }

  public Integer getFollowing() {
    return following;
  }

  public void setFollowing(Integer following) {
    this.following = following;
  }
}
