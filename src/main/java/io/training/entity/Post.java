package io.training.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.*;
import io.training.util.BaseEntity;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = {"user"})
@EqualsAndHashCode
@JsonIgnoreProperties({"comments","deleteStatus","createdOn","updatedOn"})
public class Post extends BaseEntity {

  @Column
  @NotEmpty
  @NotNull
  private String title;

  @Lob
  @NotEmpty
  @NotNull
  private String body;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id" , nullable = false)
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonProperty("userId")
  @Setter(AccessLevel.NONE)
  private User user;

  @OneToMany(mappedBy = "post" ,fetch = FetchType.LAZY,
             cascade = CascadeType.ALL,orphanRemoval = true)
  private Set<Comment> comments;

  @JsonProperty("userId")
  public void setUserById(Long userId){
    user=User.fromId(userId);
  }

  public static Post fromId(Long postId){
    Post post=new Post();
    post.setId(postId);
    //post.id=postId;
    return post;
  }

}
