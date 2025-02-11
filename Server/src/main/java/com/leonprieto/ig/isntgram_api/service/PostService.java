package com.leonprieto.ig.isntgram_api.service;

import com.leonprieto.ig.isntgram_api.model.Posts;
import com.leonprieto.ig.isntgram_api.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

  private final IPostRepository postRepository;

  @Autowired
  public PostService(IPostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Posts> getAllPosts(String userId) {
    return postRepository.findByUser_Id(userId);
  }
}
