package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String firstName;
    @Setter
    @Column(name = "surname")
    private String lastName;

    @ManyToMany
    private final List<Lecture> attending = new ArrayList<>();

    //@ManyToMany
    //private final List<Lecture> completed = new ArrayList<>();

}
