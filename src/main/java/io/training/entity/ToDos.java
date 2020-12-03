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

    @Column(name="USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST})
    User user;

    @Column
    private String title;

    @Column
    private boolean completed;


    @Column
    private String DeleteStatus;
}
