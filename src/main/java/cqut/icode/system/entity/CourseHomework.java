package cqut.icode.system.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author tq
 * @date 2019/12/18
 */
@Data
@Table(name = "ktp_course_homework")
public class CourseHomework {
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "homework_id")
    private Long homeworkId;
}
