package cqut.icode.system.mapper;

import cqut.icode.common.mapper.MyMapper;
import cqut.icode.system.entity.perm.Role;

import java.util.List;

/**
 * @author tq
 */
public interface RoleMapper extends MyMapper<Role> {

    List<Role> findUserRole(String username);
}
