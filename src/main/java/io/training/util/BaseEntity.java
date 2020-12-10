package io.training.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;


    @Column(name = "created_on")
    private LocalDateTime createdOn;

/*    @Column(name = "created_by")
    private String createdBy;*/

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

/*    @Column(name = "updated_by")
    private String updatedBy;*/

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
        setDeleteStatus(DeleteStatus.AVAILABLE);
       // createdBy = LoggedUser.get();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
        //updatedBy = LoggedUser.get();
    }


}
