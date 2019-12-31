package cqut.icode.system.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class UserCourseHomeworkIds implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "homework_id")
    private Long homeworkId;
}
