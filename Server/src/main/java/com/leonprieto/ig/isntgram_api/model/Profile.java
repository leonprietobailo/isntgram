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
public class Profile {

  @Id
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)

  private User user;

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
}
