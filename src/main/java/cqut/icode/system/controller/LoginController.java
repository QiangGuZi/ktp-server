package cqut.icode.system.controller;

import cqut.icode.common.controller.BaseController;
import cqut.icode.common.dto.ApiSuccessResponse;
import cqut.icode.common.dto.LoginItem;
import cqut.icode.common.utils.captcha.AbstractCaptcha;
import cqut.icode.common.utils.captcha.GifCaptcha;
import cqut.icode.system.entity.User;
import cqut.icode.system.service.UserService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tq
 */
@RestController
public class LoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 暂时不用验证码
     *
     * @return .
     * @throws Exception
     */
    @PostMapping("/auth/login")
    public ApiSuccessResponse login(@RequestBody LoginItem loginItem) throws Exception {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(
                    loginItem.getUsername(),
                    loginItem.getPassword(),
                    loginItem.getPassword());
            Subject subject = getSubject();
            if (subject != null) {
                subject.logout();
            }
            super.login(token);
            logger.info("是否登录==>{}", subject.isAuthenticated());
            return ApiSuccessResponse.success(super.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 注册学生
     */
    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiSuccessResponse addUser(@RequestBody User user) {
        userService.insertUser(user);
        return ApiSuccessResponse.success();
    }

    /**
     * 暂时不用
     */
    @GetMapping("/gifCode")
    public String getCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setContentType("image/gif");
            AbstractCaptcha captcha = new GifCaptcha(146, 33, 4);
            captcha.out(response.getOutputStream());

            HttpSession session = request.getSession(true);
            session.removeAttribute("gifCode");
            session.setAttribute("gifCode", captcha.text().toLowerCase());

            return captcha.text().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("获取验证码异常");
        }
    }
}
