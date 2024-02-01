package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;


    //cascade : 영속성 전이
    // cascadeType.all 이면 : persist , remove 설정 다 가능 
    @OneToMany(mappedBy = "advisor", cascade = CascadeType.PERSIST)
    private final List<Student> advisingStudents = new ArrayList<>();

}
