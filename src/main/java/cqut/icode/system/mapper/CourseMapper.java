package cqut.icode.system.mapper;

import cqut.icode.common.mapper.MyMapper;
import cqut.icode.system.entity.Course;
import cqut.icode.system.entity.Homework;
import cqut.icode.system.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 找到课程的<b>全部</b>作业，若用户是老师再加一些统计信息
     *
     * @param courseId 课程id
     * @param isCourseOwner 用户是不是老师
     * @return 作业列表
     */
    List<HashMap<String, Object>> findAllHomeworkStatisticsInfoByCourseId(Long courseId, Boolean isCourseOwner);

    /**
     * 获取所有课程的加课码
     *
     * @return 。
     */
    List<String> findAllCode();

    /**
     * 根据加课码获取课程 Id
     * @param code 加课码
     * @return  。
     */
    Long findCourseIdByCode(String code);

    /**
     * 找到某一门课程的基本信息、作业信息、学生信息等
     * @param courseId 。
     * @return 。
     */
    Map<String, Object> findCourseInfoByCourseId(Long courseId);

    /**
     * 已融合进 {findCourseInfoByCourseId} 方法
     *
     * 找到某一个门课程的统计信息，如发布作业次数、(todo: 互动数、册数次数)
     * @param courseId 。
     * @return 。
     */
    Map<String, Object> findCourseStatisticsInfoByCourseId(Long courseId);
}
