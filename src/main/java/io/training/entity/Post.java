package io.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

}
