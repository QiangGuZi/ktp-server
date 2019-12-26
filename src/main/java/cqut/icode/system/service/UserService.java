package cqut.icode.system.service;

import cqut.icode.common.service.BaseService;
import cqut.icode.system.entity.Course;
import cqut.icode.system.entity.User;

import java.util.HashMap;
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
     * topLists: [...],
     * lists: [...]
     * }
     *
     * @param userId 当前用户的 id
     * @return 如上
     */
    public Map<String, List> getCourseByUser(Long userId);


    /**
     * 权限用到
     *
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 置顶状态取反
     *
     * @param userId   .
     * @param courseId .
     */
    void changeCourseTopStatus(Long userId, Long courseId);

    /**
     * 归档状态取反
     *
     * @param userId   .
     * @param courseId .
     */
    void changeCoursePigeonholeStatus(Long userId, Long courseId);

    /**
     * 课程重新排序
     *
     * @param userId    .
     * @param courseIds .
     */
    void changeCoursePriority(Long userId, Long[] courseIds);

    /**
     * 用户加入某门课程
     *
     * @param userId .
     * @param code 加课码
     */
    void joinCourse(Long userId, String code);

    /**
     * 用户创建某门课程
     *
     * @param userId .
     * @param course 课程的一些基本信息
     */
    void createCourse(Long userId, Course course);

    List<HashMap<String, Object>> getCoursesOfPigeonholeByUser(Long userId);
}
