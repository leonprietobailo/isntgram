package com.leonprieto.ig.isntgram_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {

  @GetMapping("get")
  public ResponseEntity<String> get() {
    return ResponseEntity.ok("Hello World");
  }
}
