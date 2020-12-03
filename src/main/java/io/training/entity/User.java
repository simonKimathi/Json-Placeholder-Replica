package io.training.entity;

import java.util.Objects;
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
public class User {

  @Column(name="USER_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column
  private String name;
  @Column(unique = true)
  private String username;
  @Column(unique = true)
  private String email;
  @Embedded
  Address address;
  @Column
  private String phone;
  @Column
  private String website;
  @Embedded
  private Company company;

  @Column
  @Enumerated(EnumType.STRING)
  private DeleteStatus deleteStatus;



}
