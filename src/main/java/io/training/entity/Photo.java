package io.training.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.training.util.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table
@ToString(exclude = {"album"})
@Getter
@Setter
@NoArgsConstructor
public class Photo extends BaseEntity {

    @Column
    @NotEmpty
    @NotNull
    private String title;

    @Column
    @NotEmpty
    @NotNull
    private String url;

    @Column
    @NotEmpty
    @NotNull
    private String thumbnailUrl;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("albumId")
    @Setter(AccessLevel.NONE)
    private Album album;

    @JsonProperty("albumId")
    public void setAlbumById(long albumId) {
        album = Album.fromId(albumId);
    }

}
