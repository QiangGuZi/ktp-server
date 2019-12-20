package cqut.icode.system.controller;

import cqut.icode.common.dto.ApiSuccessResponse;
import cqut.icode.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tq
 * @date 2019/12/18
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 应由 token 获取对应的用户，返回对应的课程
     * 暂时写死，为了检查，呜呜
     * todo: 记得整理权限
     * @return 返回当前用户的课程信息
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private ApiSuccessResponse getCurrentUserCourse() {
        return ApiSuccessResponse.success(userService.getCourseByUser(1L));
    }
}
