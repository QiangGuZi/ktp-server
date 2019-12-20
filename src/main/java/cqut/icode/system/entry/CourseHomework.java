package cqut.icode.system.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

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
