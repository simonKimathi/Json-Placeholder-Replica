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
  @ManyToOne(fetch = FetchType.EAGER)
  User user;
  @Column
  private String title;
  @Column
  private String body;

  @Column(length = 255, columnDefinition = "varchar(255) default 'AVAILABLE'")
  @Enumerated(EnumType.STRING)
  private DeleteStatus deleteStatus;

}
