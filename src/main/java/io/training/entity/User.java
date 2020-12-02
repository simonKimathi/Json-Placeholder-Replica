package io.training.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class User {

  @Column(name="USER_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column
  private String name;
  @Column
  private String username;
  @Column
  private String email;
  @Embedded
  Address address;
  @Column
  private String phone;
  @Column
  private String website;
  @Embedded
  private Company company;



}
