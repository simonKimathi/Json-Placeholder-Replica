package io.training.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Comment {
    @Column(name="COMMENT_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER  )
    private Post post;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String body;

    @Column(length = 255, columnDefinition = "varchar(255) default 'AVAILABLE'")
    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

}
