package io.training.entity;

import com.fasterxml.jackson.annotation.*;
import io.training.entity.Commons.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
@ToString(exclude = {"user"})
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"photos","deleteStatus","createdOn","updatedOn"})
public class Album extends BaseEntity {

    @Column
    @NotEmpty
    @NotNull
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("userId")
    @Setter(AccessLevel.NONE)
    private User user;

    @OneToMany(mappedBy = "album" ,fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Photo> photos;

    @JsonProperty("userId")
    public void setUserById(Long userId) {
        user = User.fromId(userId);
    }

    public static Album fromId(Long albumId) {
        Album album = new Album();
        album.setId(albumId);
        return album;
    }
}
