package com.leonprieto.ig.isntgram_api.repository;

import com.leonprieto.ig.isntgram_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
  List<Post> findByUser_IdOrderByPostedDateDesc(String userId);

}
