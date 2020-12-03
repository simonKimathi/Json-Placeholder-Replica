package io.training.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ToDos {

    @Column(name="TODOS_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    User user;

    @Column
    private String title;

    @Column
    private boolean completed;


    @Column(length = 255, columnDefinition = "varchar(255) default 'AVAILABLE'")
    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;
}
