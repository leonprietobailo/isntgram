package com.leonprieto.ig.isntgram_api.service;

import com.leonprieto.ig.isntgram_api.config.ImageConfig;
import com.leonprieto.ig.isntgram_api.model.Post;
import com.leonprieto.ig.isntgram_api.model.User;
import com.leonprieto.ig.isntgram_api.repository.IPostRepository;
import com.leonprieto.ig.isntgram_api.utils.FileTypeDetectionUtils;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

  private final IPostRepository postRepository;

  private final ImageConfig imageConfig;

  private final EntityManager entityManager;

  @Autowired
  public PostService(IPostRepository postRepository, ImageConfig imageConfig,
      EntityManager entityManager) {
    this.postRepository = postRepository;
    this.imageConfig = imageConfig;
    this.entityManager = entityManager;
  }

  public List<Post> getAllPosts(String userId) {
    return postRepository.findByUser_IdOrderByPostedDateDesc(userId);
  }

  public void savePost(String userId, MultipartFile file) throws IOException {
    Path path = Paths.get(
        imageConfig.getPath() + UUID.randomUUID() + "." + FileTypeDetectionUtils.detectFileExtension(
            file));
    Files.write(path, file.getBytes());
    final Post post = new Post();
    post.setUrl(path.getFileName().toString());
    post.setPostedDate(new Date());
    post.setUser(entityManager.getReference(User.class, userId));
    postRepository.save(post);
  }


}
