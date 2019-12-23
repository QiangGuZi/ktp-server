package cqut.icode.system.entity;

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
@Table(name = "ktp_user_course")
public class UserCourse {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "course_id")
    private String courseId;
    private Boolean pigeonhole;
    private Boolean top;
    private Integer priority;
    private Integer role;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;
}
