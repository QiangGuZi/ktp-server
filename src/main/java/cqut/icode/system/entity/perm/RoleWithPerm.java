package cqut.icode.system.entity.perm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 为什么创建这个类？
 * 请看 {@link cqut.icode.system.entity.perm.UserWithRole} 中的解释
 *
 * @author tq
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleWithPerm extends Role {

    private Long menuId;

    private List<Long> menuIds;
}
