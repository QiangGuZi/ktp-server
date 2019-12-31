package cqut.icode.system.mapper;

import cqut.icode.common.mapper.MyMapper;
import cqut.icode.system.entity.Course;
import cqut.icode.system.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tq
 * @date 2019/12/18
 */
public interface UserMapper extends MyMapper<User> {

    /**
     * 找到用户对应的所有课程id的集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<Long> findCourseIdsByUserId(Long userId);

    /**
     * 找到用户对应的所有归档课程id的集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<HashMap<String, Object>> findCourseOfPigeonholeByUserId(Long userId);

    /**
     * 找到用户对应的所有置顶、未归档课程的集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<HashMap<String, Object>> findTopCoursesByUserId(Long userId);

    /**
     * 找到用户对应的所有非置顶、未归档课程的集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<HashMap<String, Object>> findNonTopCoursesByUserId(Long userId);

    /**
     * 改变课程指定状态
     * @param userId .
     * @param courseId .
     */
    void changeCourseTopStatus(Long userId, Long courseId);

    /**
     * 改变课程归档状态
     * @param userId .
     * @param courseId .
     */
    void changeCoursePigeonholeStatus(Long userId, Long courseId);

    /**
     * 改变课程排序状态
     * @param userId .
     * @param courseId .
     * @param priority .
     */
    void changeCoursePriority(Long userId, Long courseId, Long priority);


}
