package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
public interface LectureRepository
        extends JpaRepository<Lecture, Long> {

    @Query("SELECT l " +
            "FROM Lecture l " +
            "WHERE l.startTime < 12")
    List<Lecture> findLecturesBeforeLunch();

    @Query(
            value = "SELECT * " +
                    "FROM lecture " +
                    "WHERE start_time < 12",
            // 그냥 SQL 사용 옵션
            nativeQuery = true
    )
    List<Lecture> findLecturesBeforeLunchNative();

    @Query("SELECT l FROM Lecture l " +
            "WHERE l.startTime = ?1 AND l.endTime = ?2")
    List<Lecture> findLecturesByTime(
            Integer startTime,
            Integer endTime
    );

    @Query("SELECT l FROM Lecture l " +
            "WHERE l.startTime = :start " +
            "AND l.endTime = :end")
    List<Lecture> findLecturesByTimeNamed(
            @Param("start") Integer startTime,
            @Param("end") Integer endTime
    );

    @Query(
            value = "SELECT * FROM lecture " +
                    "WHERE start_time = ?1 AND end_time = ?2 ",
            nativeQuery = true
    )
    List<Lecture> findLecturesByTimeNative(
            Integer startTime,
            Integer endTime
    );
    /**
     * <code>SELECT * FROM lecture WHERE day in ('mon', 'tue', 'wed')</code>
     */
    @Query("SELECT l FROM Lecture l WHERE l.day IN :days")
    List<Lecture> findByDayIn(
            @Param("days") Collection<String> days
    );

    // Pagination
    @Query("SELECT l FROM Lecture l WHERE l.startTime < 12")
    Page<Lecture> findLecturesBeforeLunch(Pageable pageable);

    @Query("SELECT l FROM Lecture l WHERE l.startTime < 12")
    List<Lecture> findLecturesBeforeLunch(Sort sort);

    @Query(
            value = "SELECT * FROM lecture WHERE start_time < 12",
            countQuery = "SELECT COUNT(*) FROM lecture WHERE start_time < 12",
            nativeQuery = true
    )
    Page<Lecture> findLecturesBeforeLunchNative(Pageable pageable);

    // 직접 전달할 쿼리를 작성하기 때문에
    // 'JpaRepository<T, ID' 의 T와 무관하게 사용 가능하나
    // 별로 좋은 권장되는 방식은 아니다.
    @Query("SELECT i FROM Instructor i")
    List<Instructor> findInstructorsInLectureRepository();
}