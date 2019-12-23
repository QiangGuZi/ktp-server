package cqut.icode.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author tq
 * @date 2019/12/18
 */
@Data
@Table(name = "ktp_course")
public class Course implements Serializable {
    private static final long serialVersionUID = 3554316034860494763L;
    @Id
    private Integer id;
    private String coursename;
    private String code;
    private String classname;
    @Column(name = "semester_year")
    private String semesterYear;
    private String semester;
    @Column(name = "bg_image_url")
    private String bgImageUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;
}
