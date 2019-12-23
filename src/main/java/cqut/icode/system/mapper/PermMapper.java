package cqut.icode.system.mapper;

import cqut.icode.common.mapper.MyMapper;
import cqut.icode.system.entity.perm.Perm;

import java.util.List;

/**
 * @author tq
 */
public interface PermMapper extends MyMapper<Perm> {

    List<Perm> findUserPermissions(String username);
}
