package cqut.icode.system.controller;

import cqut.icode.common.controller.BaseController;
import cqut.icode.common.dto.ApiSuccessResponse;
import cqut.icode.system.entity.Course;
import cqut.icode.system.entity.User;
import cqut.icode.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tq
 * @date 2019/12/18
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);
    /**
     * 应由 token 获取对应的用户，返回对应的课程
     * 暂时写死，为了检查，呜呜
     * todo: 记得整理权限
     * @return 返回当前用户的课程信息
     */
    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public ApiSuccessResponse getCurrentUserCourse() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Map<String, List> result = userService.getCourseByUser(user.getId());
        return ApiSuccessResponse.success(result);
    }

    /**
     * 目前是假数据
     * @return .
     */
    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public ApiSuccessResponse getCurrentUserInfo() {
        Map<String, Object> result = new HashMap<>(16);

        Map<String, Object> roles = new HashMap<>(16);
        String[] permissions = {"I can do anything!"};
        roles.put("permissions", permissions);

        result.put("roles", roles);

        return ApiSuccessResponse.success(result);
    }

    /**
     * 改变课程的置顶状态
     * @param course .
     * @return .
     */
    @PutMapping("/courses/top")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiSuccessResponse changeTop(@RequestBody Course course) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userService.changeCourseTopStatus(user.getId(), course.getId());
        return ApiSuccessResponse.success();
    }

    /**
     * 改变课程的归档状态
     * @param course .
     * @return .
     */
    @PutMapping("/courses/pigeonhole")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiSuccessResponse changePigeonhole(@RequestBody Course course) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userService.changeCoursePigeonholeStatus(user.getId(), course.getId());
        return ApiSuccessResponse.success();
    }

    /**
     * 改变课程间的优先级
     * @param courseIds .
     * @return .
     */
    @PutMapping("/courses/priority")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiSuccessResponse changePriority(@RequestBody Long[] courseIds) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        log.info("priority -->" + Arrays.asList(courseIds));
        userService.changeCoursePriority(user.getId(), courseIds);
        return ApiSuccessResponse.success();
    }

    /**
     * 用户加入某一个课程，要先验证该课程是否存在
     * @param course .
     * @return .
     */
    @PostMapping("/courses/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse joinCourse(@RequestBody Course course) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userService.joinCourse(user.getId(), course.getCode());
        return ApiSuccessResponse.success();
    }

    /**
     * 创建课程
     * @param course .
     * @return .
     */
    @PostMapping("/courses/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse createCourse(@RequestBody Course course) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userService.createCourse(user.getId(), course);
        return ApiSuccessResponse.success();
    }

    /**
     * 获取用户的归档课程
     */
    @GetMapping("/courses/pigeonhole")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse getCoursesOfPigeonhole() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<HashMap<String, Object>> result = userService.getCoursesOfPigeonholeByUser(user.getId());
        return ApiSuccessResponse.success(result);
    }
}
