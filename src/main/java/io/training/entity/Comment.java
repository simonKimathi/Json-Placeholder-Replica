package io.training.entity;

import com.fasterxml.jackson.annotation.*;
import io.training.util.BaseEntity;
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
@JsonIgnoreProperties({"deleteStatus","createdOn","updatedOn"})
public class Comment extends BaseEntity {

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
