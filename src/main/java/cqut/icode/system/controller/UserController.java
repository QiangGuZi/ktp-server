package cqut.icode.system.controller;

import cqut.icode.common.controller.BaseController;
import cqut.icode.common.dto.ApiSuccessResponse;
import cqut.icode.system.entity.Course;
import cqut.icode.system.entity.Homework;
import cqut.icode.system.entity.User;
import cqut.icode.system.entity.UserCourseHomework;
import cqut.icode.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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

    /**
     * 根据课程 Id 获取用户课程的详细信息
     */
    @GetMapping("/courses/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiSuccessResponse getCourseInfoByUser(@PathVariable("courseId") Long courseId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> result = userService.getCourseInfoByUser(user.getId(), courseId);
        return ApiSuccessResponse.success(result);
    }

    /**
     * 获取某门课程的作业列表
     */
    @GetMapping("/courses/{courseId}/homework")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse getHomeworkListByCourseId(@PathVariable("courseId") Long courseId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<HashMap<String, Object>> result = userService.getHomeworkByCourseId(user.getId(), courseId);

        return ApiSuccessResponse.success(result);
    }


    /**
     * 老师发布作业
     */
    @PostMapping("/courses/{courseId}/homework")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse publishHomework(@RequestBody Homework homework, @PathVariable("courseId") Long courseId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userService.createHomework(user.getId(), courseId, homework);
        return ApiSuccessResponse.success();
    }

    /**
     * 学生提交作业
     */
    @PostMapping("/courses/{courseId}/homework/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse uploadHomeworkByStudent(@RequestBody UserCourseHomework userCourseHomework, @PathVariable("courseId") Long courseId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userCourseHomework.setUserId(user.getId());
        userCourseHomework.setCourseId(courseId);

        userService.saveUserCourseHomework(userCourseHomework);
        return ApiSuccessResponse.success();
    }


    /**
     * 老师作业打分
     * todo: 打分之前判断当前用户是否有资格打分
     */
    @PutMapping("/courses/{courseId}/homework")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse updateHomeworkByTeacher(@RequestBody UserCourseHomework userCourseHomework, @PathVariable("courseId") Long courseId) {
        userCourseHomework.setCourseId(courseId);

        userService.updateHomeworkByTeacher(userCourseHomework);
        return ApiSuccessResponse.success();
    }

    /**
     * 老师作业批量打分
     * todo: 打分之前判断当前用户是否有资格打分
     */
    @PutMapping("/courses/{courseId}/homework/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse updateHomeworkByTeacher(@RequestBody UserCourseHomework[] userCourseHomework, @PathVariable("courseId") Long courseId) {
        for (UserCourseHomework courseHomework : userCourseHomework) {
            courseHomework.setCourseId(courseId);
        }

        userService.updateHomeworkByTeacher(userCourseHomework);
        return ApiSuccessResponse.success();
    }

    /**
     * 学生作业情况列表
     */
    @GetMapping("/courses/{courseId}/homework/{homeworkId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse getHomeworkSubmitList(@PathVariable("courseId") Long courseId, @PathVariable("homeworkId") Long homeworkId) {
        return ApiSuccessResponse.success(userService.getHomeworkList(courseId, homeworkId));
    }

    /**
     * 某门的课程的某门作业的相关信息
     */
    @GetMapping("/courses/{courseId}/homework/{homeworkId}/info")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse getHomeworkInfo(@PathVariable("courseId") Long courseId, @PathVariable("homeworkId") Long homeworkId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return ApiSuccessResponse.success(userService.getHomeworkInfo(user.getId(), courseId, homeworkId));
    }


    final String UPLOADED_FOLDER = "D:\\ktp-server\\upload\\";
    /**
     * 文件上传
     */
    @PostMapping("/file")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse getHomeworkInfo(@RequestParam("file") MultipartFile[] files) {
        StringBuilder result = new StringBuilder();
        if (files.length == 0)
        {
            return ApiSuccessResponse.success();
        }
        for (MultipartFile file : files)
        {
            try
            {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                result.append(file.getOriginalFilename()).append(";");
                Files.write(path, bytes);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return ApiSuccessResponse.success(result.toString());
    }
}
