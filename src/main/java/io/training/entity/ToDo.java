package io.training.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.training.entity.commonClasses.BaseEntity;
import io.training.entity.commonClasses.DeleteStatus;
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
public class ToDo extends BaseEntity {

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
