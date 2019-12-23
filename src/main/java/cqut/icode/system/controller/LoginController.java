package cqut.icode.system.controller;

import cqut.icode.common.controller.BaseController;
import cqut.icode.common.dto.ApiSuccessResponse;
import cqut.icode.common.utils.captcha.AbstractCaptcha;
import cqut.icode.common.utils.captcha.GifCaptcha;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tq
 */
@RestController
public class LoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 暂时不用验证码
     * @param username .
     * @param password .
     * @param remember .
     * @return .
     * @throws Exception
     */
    @PostMapping("/login")
    public ApiSuccessResponse login(String username, String password,
//                                    String code,
                                    Boolean remember) throws Exception {
//        if (StringUtils.isEmpty(code)) {
//            return new ResponseCode(StatusEnums.CODE_ERROR);
//        }
//        Session session = super.getSession();
//        String gifCode = (String) session.getAttribute("gifCode");
//        if (!code.equalsIgnoreCase(gifCode)) {
//            return new ResponseCode(StatusEnums.CODE_ERROR);
//        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember);
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

    @GetMapping("/logout")
    public ApiSuccessResponse logout() {
        getSubject().logout();
        return ApiSuccessResponse.success();
    }
}
