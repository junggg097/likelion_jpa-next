package com.example.jpanext.school;

import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import com.example.jpanext.school.repo.LectureRepository;
import com.example.jpanext.school.repo.StudentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("school")
@RequiredArgsConstructor
public class SchoolController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @GetMapping("many-to-many")
    public String test() {
        Student yujin = Student.builder()
                .firstName("yujin")
                .lastName("jung")
                .build();
        yujin = studentRepository.save(yujin);

        Student brad = Student.builder()
                .firstName("brad")
                .lastName("ford")
                .build();
        brad = studentRepository.save(brad);

        Lecture jpa = Lecture.builder()
                .name("jpa")
                .startTime(9)
                .endTime(16)
                .build();
        jpa = lectureRepository.save(jpa);

        Lecture spring = Lecture.builder()
                .name("spring boot")
                .startTime(9)
                .endTime(16)
                .build();
        spring = lectureRepository.save(spring);


        yujin.getAttending().add(jpa);
        yujin.getAttending().add(spring);
        brad.getAttending().add(spring);
        studentRepository.save(yujin);
        studentRepository.save(brad);
        lectureRepository.save(spring);


        return "done";
    }

    @GetMapping("many-to-many-get")
    public String manyToManyGet() {
        Student yujin = studentRepository.findById(1L)
                .get();
        for (Lecture lecture: yujin.getAttending()) {
            log.info("{} listens {}", yujin.getFirstName(), lecture.getName());
        }

        Lecture spring= lectureRepository.findById(2L)
                .get();
        for (Student student: spring.getStudents()) {
            log.info("{} listens {}", student.getFirstName(), spring.getName());
        }

        return "done";
    }
}
