package cqut.icode.system.service.impl;

import cqut.icode.common.service.impl.BaseServiceImpl;
import cqut.icode.system.entity.User;
import cqut.icode.system.mapper.UserMapper;
import cqut.icode.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tq
 * @date 2019/12/18
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public User findByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", username);
        List<User> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Autowired
    UserMapper userMapper;

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

        return result;
    }

    protected List<HashMap<String, Object>> putToHash(List<HashMap<String, Object>> list, Long userId) {
        for (HashMap<String, Object> map : list) {
            // 2. 获取每门课程的一些信息：老师、学生列表、作业列表、（通知列表）
            Long courseId = (Long) map.get("id");

            User courseOwner = userMapper.findCourseOwnerByCourseId(courseId);
            if (courseOwner.getId().equals(userId)) {
                map.put("owner", true);
            }
            map.put("username", courseOwner.getUsername());
            map.put("avatar", courseOwner.getAvatar());

            // 3. 获取该门课程的学生、作业
            List<HashMap<String, Object>> studentsOfCourse = userMapper.findStudentsOfCourseByCourseId(courseId);
            map.put("students", studentsOfCourse);
            List<HashMap<String, Object>> homeworkOfCourse = userMapper.findHomeworkOfCourseByCourseId(courseId);
            map.put("homework", homeworkOfCourse);
        }

        return list;
    }


}
