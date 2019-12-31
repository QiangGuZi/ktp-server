package cqut.icode.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author tq
 * @date 2019/12/18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ktp_user_course_homework")
@IdClass(UserCourseHomeworkIds.class)
public class UserCourseHomework {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "course_id")
    private Long courseId;
    @Id
    @Column(name = "homework_id")
    private Long homeworkId;
    private Boolean submit;
    private String annex;
    @Column(name = "student_work_message")
    private String studentWorkMessage;
    private Integer grade;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "modify_time")
    private Date modifyTime;
}
