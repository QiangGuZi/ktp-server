package cqut.icode.system.service.impl;

import cqut.icode.common.service.impl.BaseServiceImpl;
import cqut.icode.system.entity.perm.Perm;
import cqut.icode.system.mapper.PermMapper;
import cqut.icode.system.service.PermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tq
 */
@Service
public class PermServiceImpl extends BaseServiceImpl<Perm> implements PermService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermMapper permMapper;


    @Override
    public List<Perm> findUserPermissions(String username) {
        return permMapper.findUserPermissions(username);
    }
}
