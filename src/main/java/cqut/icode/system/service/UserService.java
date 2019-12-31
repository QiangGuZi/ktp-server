package cqut.icode.system.service;

import cqut.icode.common.service.BaseService;
import cqut.icode.system.entity.Course;
import cqut.icode.system.entity.Homework;
import cqut.icode.system.entity.User;
import cqut.icode.system.entity.UserCourseHomework;

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
    Map<String, List> getCourseByUser(Long userId);


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
     * @param code   加课码
     */
    void joinCourse(Long userId, String code);

    /**
     * 用户创建某门课程
     *
     * @param userId .
     * @param course 课程的一些基本信息
     */
    void createCourse(Long userId, Course course);

    /**
     * 获取用户的归档课程
     *
     * @param userId .
     * @return ,
     */
    List<HashMap<String, Object>> getCoursesOfPigeonholeByUser(Long userId);

    /**
     * 获取用户某一门课程的基本信息、作业信息等
     * 如果自己是 教师 ，那就再加上一些统计信息
     *
     * @param userId
     * @param courseId .
     * @return .
     */
    Map<String, Object> getCourseInfoByUser(Long userId, Long courseId);

    /**
     * 用户创建某门课程的作业
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @param homework 课程的一些基本信息
     */
    void createHomework(Long userId, Long courseId, Homework homework);

    /**
     * 用户获取某门课程的作业列表
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return .
     */
    List<HashMap<String, Object>> getHomeworkByCourseId(Long userId, Long courseId);

    /**
     * 用户上传作业
     *
     * @param userCourseHomework 。
     */
    void saveUserCourseHomework(UserCourseHomework userCourseHomework);

    /**
     * 老师作业打分
     *
     * @param userCourseHomework 。
     */
    void updateHomeworkByTeacher(UserCourseHomework userCourseHomework);

    /**
     * 老师作业打批量分
     *
     * @param userCourseHomework 。
     */
    void updateHomeworkByTeacher(UserCourseHomework[] userCourseHomework);

    /**
     * 获取课程莫作业的提交情况
     *
     * @param courseId   .
     * @param homeworkId .
     * @return .
     */
    List<HashMap<String, Object>> getHomeworkList(Long courseId, Long homeworkId);

    /**
     * 获取用户对该作业的一些情况
     *
     * @param courseId   .
     * @param homeworkId .
     */
    HashMap<String, Object> getHomeworkInfo(Long userId, Long courseId, Long homeworkId);

    /**
     * 用户注册
     * @param user
     */
    void insertUser(User user);
}
