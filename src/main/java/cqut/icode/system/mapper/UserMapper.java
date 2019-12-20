package cqut.icode.system.mapper;

import cqut.icode.common.mapper.MyMapper;
import cqut.icode.system.entry.Homework;
import cqut.icode.system.entry.User;

import java.util.HashMap;
import java.util.List;

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
     * 找到课程的教师（所有者）
     *
     * @param courseId 课程id
     * @return 教师信息
     */
    User findCourseOwnerByCourseId(Long courseId);

    /**
     * 找到课程的学生，最多三个
     *
     * @param courseId 课程id
     * @return 学生列表
     */
    List<HashMap<String, Object>> findStudentsOfCourseByCourseId(Long courseId);

    /**
     * 找到课程的历史作业，最多两个，按时间倒序
     *
     * @param courseId 课程id
     * @return 作业列表
     */
    List<HashMap<String, Object>> findHomeworkOfCourseByCourseId(Long courseId);
}
