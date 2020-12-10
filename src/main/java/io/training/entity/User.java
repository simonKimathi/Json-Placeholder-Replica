package io.training.entity;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.training.util.BaseEntity;
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
@JsonIgnoreProperties({"posts"})
public class User extends BaseEntity {

  @Column
  @NotEmpty
  @NotNull
  private String name;

  @Column(unique = true)
  @NotEmpty
  @NotNull
  private String username;

  @Column(unique = true)
  @NotEmpty
  @NotNull
  private String email;

  @Embedded
  Address address;

  @Column
  @NotEmpty
  @NotNull
  private String phone;

  @Column
  private String website;

  @Embedded
  private Company company;

  @OneToMany(mappedBy ="user", fetch = FetchType.LAZY,
          cascade = CascadeType.ALL,orphanRemoval = true)
  private Set<Post> posts;

  public static User fromId(long userId){
    User user=new User();
    user.setId(userId);
    return user;
  }



}
