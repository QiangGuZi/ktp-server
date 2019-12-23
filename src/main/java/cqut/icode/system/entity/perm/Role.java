package cqut.icode.system.entity.perm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author tq
 */
@Data
@ToString
@Table(name = "ktp_role")
public class Role implements Serializable {

    @Id
    private Long id;

    private String name;

    private String description;

    private Boolean status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    public void setName(String name) {
        this.name = name == null ? "" : name.trim();
    }
}
