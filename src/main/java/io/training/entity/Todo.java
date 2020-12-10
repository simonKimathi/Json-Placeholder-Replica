package io.training.entity;

import com.fasterxml.jackson.annotation.*;
import io.training.entity.Commons.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table
@ToString(exclude = {"user"})
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"deleteStatus","createdOn","updatedOn"})
public class Todo extends BaseEntity {

    @Column
    @NotEmpty
    @NotNull
    private String title;

    @Column
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("userId")
    @Setter(AccessLevel.NONE)
    private User user;

    @JsonProperty("userId")
    public void setUserById(Long userId) {
        user = User.fromId(userId);
    }


}
