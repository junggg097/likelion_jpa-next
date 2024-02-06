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

    //@Setter
    //private String name;
    //@Setter
    // 컬럼 이름을 설정 하고 싶을 때 ( 그 외의 기능도 많음 )
    //@Column(name = "last_name")
    //private String lastName;


    private String name;
    private Integer age;
    private String phone;
    private String email;

    @ManyToMany
    // Join Table 의 모습을 정의 하고 싶을 때
    @JoinTable(
            name="attending_lectures",
            // Join Table 의 나를 가르키는 FK 설정
            joinColumns = @JoinColumn(name = "student_id"),
            // Join Table 의 관계를 맺는 상대방의 가르키는 FK의 설정
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    private final List<Lecture> attending = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private final List<AttendingLectures> attendingLectures = new ArrayList<>();

    //@ManyToMany
    //private final List<Lecture> completed = new ArrayList<>();

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id")
    private Instructor advisor;
}
