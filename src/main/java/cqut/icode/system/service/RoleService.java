package cqut.icode.system.service;

import cqut.icode.common.service.BaseService;
import cqut.icode.system.entity.perm.Role;

import java.util.List;

/**
 * @author tq
 */
public interface RoleService extends BaseService<Role> {
    List<Role> findUserRole(String username);
}
