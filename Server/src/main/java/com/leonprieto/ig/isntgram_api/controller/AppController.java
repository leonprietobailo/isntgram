package com.leonprieto.ig.isntgram_api.controller;

import com.leonprieto.ig.isntgram_api.model.Posts;
import com.leonprieto.ig.isntgram_api.model.UserProfile;
import com.leonprieto.ig.isntgram_api.service.AppService;
import com.leonprieto.ig.isntgram_api.service.PostService;
import com.leonprieto.ig.isntgram_api.service.response.GenericApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/app")
public class AppController {

  private final AppService appService;

  private final PostService postService;

  @Autowired
  public AppController(AppService appService, PostService postService) {
    this.appService = appService;
    this.postService = postService;
  }

  @GetMapping("profiles/{userId}")
  public ResponseEntity<UserProfile> getProfile(@PathVariable String userId) {
    final Optional<UserProfile> profile = appService.getProfile(userId);
    return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("profiles/update")
  public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile userProfile) {
    final UserProfile result = appService.updateProfile(userProfile);
    return ResponseEntity.ok(result);
  }

  @GetMapping("profiles/search/{query}")
  public List<UserProfile> getProfilesByQuery(@PathVariable String query) {
    return appService.searchProile(query);
  }

  @PostMapping("upload/posts")
  public ResponseEntity<GenericApiResponse> uploadPost(@RequestParam String userId,
      @RequestParam MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    try {
      postService.savePost(userId, file);
      return ResponseEntity.ok().build();
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("profiles/{userId}/posts")
  public List<Posts> getAllPosts(@PathVariable String userId) {
    return postService.getAllPosts(userId);
  }
}
