package cqut.icode.system.service.impl;

import cqut.icode.common.service.impl.BaseServiceImpl;
import cqut.icode.system.entity.perm.Role;
import cqut.icode.system.mapper.RoleMapper;
import cqut.icode.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tq
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findUserRole(String username) {
        return roleMapper.findUserRole(username);
    }
}
