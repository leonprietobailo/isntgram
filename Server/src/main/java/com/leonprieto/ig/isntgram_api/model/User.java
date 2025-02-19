package com.leonprieto.ig.isntgram_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private String id;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  @JoinColumn(nullable = false)
  @OneToOne(mappedBy = "user")
  private Profile profile;
}
