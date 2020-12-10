package io.training.entity;

import com.fasterxml.jackson.annotation.*;
import io.training.entity.Commons.BaseEntity;
import io.training.entity.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"post"})
@JsonIgnoreProperties({"deleteStatus","createdOn","updatedOn"})
public class Comment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("postId")
    @Setter(AccessLevel.NONE)
    private Post post;

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
    @Lob
    private String body;

    @JsonProperty("postId")
    public void setPostById(long postId) {
        post = Post.fromId(postId);
    }


}
