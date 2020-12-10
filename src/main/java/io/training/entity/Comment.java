package io.training.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = {"post"})
@EqualsAndHashCode
public class Comment {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @NotEmpty
    private String name;

    @Column
    @NotNull
    @NotEmpty
    private String email;

    @Column
    @NotNull
    @NotEmpty
    private String body;

    @Column
    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("postId")
    @Setter(AccessLevel.NONE)
    private Post post;

    @JsonProperty("postId")
    public void setPostById(long postId) {
        post = Post.fromId(postId);
    }

}
