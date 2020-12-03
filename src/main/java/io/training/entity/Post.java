package io.training.entity;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Post {
  @Column(name="POST_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST})
  User user;
  @Column
  private String title;
  @Column
  private String body;

  @Column
  @Enumerated(EnumType.STRING)
  private DeleteStatus deleteStatus;

}
