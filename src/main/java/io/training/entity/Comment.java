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
    @Column(name="POST_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL  )
    private Post post;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String body;

}
