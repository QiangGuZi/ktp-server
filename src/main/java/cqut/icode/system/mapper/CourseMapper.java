package cqut.icode.system.mapper;

import cqut.icode.common.mapper.MyMapper;
import cqut.icode.system.entity.Course;
import cqut.icode.system.entity.Homework;
import cqut.icode.system.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author tq
 * @date 2019/12/18
 */
public interface CourseMapper extends MyMapper<Course> {

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
    List<HashMap<String, Object>> findSomeStudentsOfCourseByCourseId(Long courseId);

    /**
     * 找到课程的<b>全部</b>学生
     *
     * @param courseId 课程id
     * @return 学生列表
     */
    List<User> findAllStudentsOfCourseByCourseId(Long courseId);

    /**
     * 找到课程的历史作业，最多两个，按时间倒序
     *
     * @param courseId 课程id
     * @return 作业列表
     */
    List<HashMap<String, Object>> findSomeHomeworkOfCourseByCourseId(Long courseId);

    /**
     * 找到课程的<b>全部</b>作业
     *
     * @param courseId 课程id
     * @return 作业列表
     */
    List<Homework> findAllHomeworkOfCourseByCourseId(Long courseId);

    /**
     * 获取所有课程的加课码
     *
     * @return 。
     */
    List<String> findAllCode();

    /**
     * 根据加课码获取课程 Id
     *
     * @return  。
     */
    Long findCourseIdByCode(String code);
}
