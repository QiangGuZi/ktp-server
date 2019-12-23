package cqut.icode.system.service;

import cqut.icode.common.service.BaseService;
import cqut.icode.system.entity.perm.Perm;

import java.util.List;

/**
 * @author tq
 */
public interface PermService extends BaseService<Perm> {
    List<Perm> findUserPermissions(String username);
}
