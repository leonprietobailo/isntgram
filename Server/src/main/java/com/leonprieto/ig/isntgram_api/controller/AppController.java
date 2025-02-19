package com.leonprieto.ig.isntgram_api.controller;

import com.leonprieto.ig.isntgram_api.model.Post;
import com.leonprieto.ig.isntgram_api.model.Profile;
import com.leonprieto.ig.isntgram_api.service.AppService;
import com.leonprieto.ig.isntgram_api.service.PostService;
import com.leonprieto.ig.isntgram_api.service.response.GenericApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
  public ResponseEntity<Profile> getProfile(@PathVariable String userId) {
    final Optional<Profile> profile = appService.getProfile(userId);
    return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("profiles/update")
  public ResponseEntity<Profile> updateProfile(@RequestBody Profile userProfile) {
    final Profile result = appService.updateProfile(userProfile);
    return ResponseEntity.ok(result);
  }

  @GetMapping("profiles/search/{query}")
  public List<Profile> getProfilesByQuery(@PathVariable String query) {
    return appService.searchProile(query);
  }

  @PostMapping(value = "upload/posts/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<GenericApiResponse> uploadPost(@PathVariable String userId,
      @RequestParam("image") MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    try {
      postService.savePost(userId, file);
      return ResponseEntity.ok(new GenericApiResponse(true, "Post uploaded successfully"));
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("profiles/{userId}/posts")
  public List<Post> getAllPosts(@PathVariable String userId) {
    return postService.getAllPosts(userId);
  }
}
