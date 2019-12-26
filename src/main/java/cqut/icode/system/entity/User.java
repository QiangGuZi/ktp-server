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
@Table(name = "ktp_user")
public class User implements Serializable {

    private static final long serialVersionUID = 3554316034860494763L;

    @Id
    private Long id;
    private String account;
    private String username;
    private String password;
    private String salt;
    private String avatar;
    private String mobile;
    private String email;
    private String school;
    private String sno;
    private Integer role;
    private String department;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "modify_time")
    private Date modifyTime;

    public void setAccount(String account) {
        this.account = account == null ? "" : account.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password.trim();
    }
}
