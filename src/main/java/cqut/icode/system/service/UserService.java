package cqut.icode.system.service;

import cqut.icode.common.service.BaseService;
import cqut.icode.system.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author tq
 * @date 2019/12/18
 */
public interface UserService extends BaseService<User> {
    /**
     * 获取当前用户的课程
     * {
     *     topLists: [...],
     *     lists: [...]
     * }
     * @param userId 当前用户的 id
     * @return 如上
     */
    public Map<String, List> getCourseByUser(Long userId);


    /**
     * 权限用到
     * @param username
     * @return
     */
    User findByName(String username);
}
