package cqut.icode.system.service.impl;

import cqut.icode.common.dto.SysConstant;
import cqut.icode.common.exception.GlobalException;
import cqut.icode.common.service.impl.BaseServiceImpl;
import cqut.icode.common.utils.JoinCourseCode;
import cqut.icode.system.entity.*;
import cqut.icode.system.mapper.CourseMapper;
import cqut.icode.system.mapper.UserCourseHomeworkMapper;
import cqut.icode.system.mapper.UserCourseMapper;
import cqut.icode.system.mapper.UserMapper;
import cqut.icode.system.service.CourseService;
import cqut.icode.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author tq
 * @date 2019/12/18
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    CourseService courseService;

    @Autowired
    UserMapper userMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    UserCourseMapper userCourseMapper;
    @Autowired
    UserCourseHomeworkMapper userCourseHomeworkMapper;

    /**
     * 根据用户名获取其基本信息
     *
     * @param username .
     * @return .
     */
    @Override
    public User findByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", username);
        List<User> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void changeCourseTopStatus(Long userId, Long courseId) {
        userMapper.changeCourseTopStatus(userId, courseId);
    }

    @Override
    public void changeCoursePigeonholeStatus(Long userId, Long courseId) {
        userMapper.changeCoursePigeonholeStatus(userId, courseId);
    }

    @Override
    public void changeCoursePriority(Long userId, Long[] courseIds) {
        for (long i = 0; i < courseIds.length; i++) {
            userMapper.changeCoursePriority(userId, courseIds[(int) i], i);
        }
    }

    /**
     * 用户加入某门课程
     * 1. 建立用户与该门课程的联系
     * 2. 建立用户与该门课程作业的联系
     *
     * @param userId .
     * @param code   加课码
     */
    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = GlobalException.class)
    public void joinCourse(Long userId, String code) {
        if (code == null || code.isEmpty()) {
            throw new GlobalException("加课码失效/不存在");
        }
        // 检查加课码存在与否
        Example example = new Example(Course.class);
        example.createCriteria().andCondition("upper(code)=", code.toUpperCase());
        List<Course> courseList = courseService.selectByExample(example);

        // 加课码存在
        if (courseList.size() == 1) {
            Long courseId = courseList.get(0).getId();

            // 检查是否加入过该门课程
            Example userCourseExample = new Example(UserCourse.class);
            userCourseExample.createCriteria()
                    .andEqualTo("userId", userId)
                    .andEqualTo("courseId", courseId);
            List<UserCourse> userCourseList = userCourseMapper.selectByExample(userCourseExample);

            // 已经加过该门课了
            if (userCourseList.size() == 1) {
                throw new GlobalException("您已经加入该门课程");
            }

            UserCourse userCourse = new UserCourse();
            userCourse.setUserId(userId);
            userCourse.setCourseId(courseList.get(0).getId());
            userCourse.setPigeonhole(false);
            userCourse.setTop(false);
            userCourse.setPriority(99999L);
            userCourse.setRole(0);
            userCourse.setCreateTime(new Date());

            // 1. 建立用户与该门课程的联系
            userCourseMapper.insert(userCourse);

            // todo: 建立用户与该门课程所有作业的联系
            // 先找到所有作业
            List<Homework> homeworkList = courseMapper.findAllHomeworkOfCourseByCourseId(courseId);
            List<UserCourseHomework> userCourseHomeworkList = new ArrayList<>();

            for (Homework homework : homeworkList) {
                UserCourseHomework userCourseHomework = new UserCourseHomework(userId, courseId, homework.getId(),
                        false, "", "", null, new Date(), null);
                userCourseHomeworkList.add(userCourseHomework);
            }

            // 2. 建立用户与该门课程所有作业的联系
            if (!userCourseHomeworkList.isEmpty()) {
                userCourseHomeworkMapper.insertList(userCourseHomeworkList);
            }
        } else {
            throw new GlobalException("加课码失效/不存在");
        }
    }

    /**
     * 用户创建某个课程
     * 随机生成验证码，要求不能重复
     *
     * @param userId .
     * @param course 课程的一些基本信息
     */
    @Override
    public void createCourse(Long userId, Course course) {
        if (userId == null) {
            throw new GlobalException("创建课程失败，请再次输入！");
        }

        List<String> existingCodes = courseMapper.findAllCode();
        // 1. 生成加课码等基本信息
        String code = JoinCourseCode.generateNotRepeat(existingCodes);
        course.setCode(code);
        course.setCreateTime(new Date());
        if (course.getBgImageUrl() == null || course.getBgImageUrl().isEmpty()) {
            course.setBgImageUrl(SysConstant.DEFAULT_URL);
        }

        // 2. 插入课程，然后获取他的 id
        courseMapper.insert(course);
        Long courseId = courseMapper.findCourseIdByCode(code);

        // 3. 建立用户与课程的联系
        UserCourse userCourse = new UserCourse(userId, courseId, false, true, 0L, 1, new Date());
        userCourseMapper.insert(userCourse);
    }

    @Override
    public List<HashMap<String, Object>> getCoursesOfPigeonholeByUser(Long userId) {
        if (userId == null) {
            throw new GlobalException("获取归档课程失败");
        }
        List<HashMap<String, Object>> courseList = userMapper.findCourseOfPigeonholeByUserId(userId);
        for (HashMap<String, Object> courseMap : courseList) {
            User courseOwner = courseMapper.findCourseOwnerByCourseId((Long) courseMap.get("id"));
            if (courseOwner.getId().equals(userId)) {
                courseMap.put("owner", true);
            }
            courseMap.put("account", courseOwner.getAccount());
            courseMap.put("avatar", courseOwner.getAvatar());
        }
        return courseList;
    }


    /**
     * 获取所有的课程
     * 获取当前课程的老师、学生列表(<=3)、作业列表(<=2)
     * 判断自己是不是老师
     * <p>
     * 1.根据 userID 获取到所有课程
     *
     * @param userId 当前用户的 id
     * @return
     */
    @Override
    public Map<String, List> getCourseByUser(Long userId) {
        // 1.获取（课程&用户与课程）的一些信息  置顶课程
        List<HashMap<String, Object>> topList = userMapper.findTopCoursesByUserId(userId);
        List<HashMap<String, Object>> nonTopList = userMapper.findNonTopCoursesByUserId(userId);

        Map<String, List> result = new HashMap<>(16);
        result.put("topList", putToHash(topList, userId));
        result.put("list", putToHash(nonTopList, userId));
        result.put("pigeonholeList", this.getCoursesOfPigeonholeByUser(userId));

        return result;
    }


    /**
     * 将获取的一些基本信息转成map结构 。
     *
     * @param userId 。
     * @return 。
     */
    protected List<HashMap<String, Object>> putToHash(List<HashMap<String, Object>> list, Long userId) {
        for (HashMap<String, Object> map : list) {
            // 2. 获取每门课程的一些信息：老师、学生列表、作业列表、（通知列表）
            Long courseId = (Long) map.get("id");

            // 找到课程的老师
            User courseOwner = courseMapper.findCourseOwnerByCourseId(courseId);
            if (courseOwner.getId().equals(userId)) {
                map.put("owner", true);
            }
            map.put("account", courseOwner.getAccount());
            map.put("avatar", courseOwner.getAvatar());

            // 3. 获取该门课程的学生、作业
            List<HashMap<String, Object>> studentsOfCourse = courseMapper.findSomeStudentsOfCourseByCourseId(courseId);
            map.put("students", studentsOfCourse);
            List<HashMap<String, Object>> homeworkOfCourse = courseMapper.findSomeHomeworkOfCourseByCourseId(courseId);
            map.put("homework", homeworkOfCourse);
        }

        return list;
    }

}
