package cqut.icode.system.entity.perm;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tq
 */
@Data
@ToString
@Table(name = "ktp_user_role")
public class UserRole implements Serializable {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role_id")
    private long roleId;
}
