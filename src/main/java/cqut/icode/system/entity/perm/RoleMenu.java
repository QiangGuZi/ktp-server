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
@Table(name = "ktp_role_perm")
public class RoleMenu implements Serializable {

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "perm_id")
    private Long menuId;
}
