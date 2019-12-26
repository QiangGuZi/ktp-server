package cqut.icode.common.handle;

import cqut.icode.common.dto.ApiErrorResponse;
import cqut.icode.common.exception.GlobalException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tq
 * @date 2019/12/18
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandle {
    // todo: 未授权异常处理
//    @ExceptionHandler(value = AuthorizationException.class)
//    public Object handleAuthorizationException(Exception e, HttpServletRequest request) {
//        e.printStackTrace();
//        if (HttpUtil.isAjaxRequest(request)) {
//            return new ResponseCode(StatusEnums.PERMISSION_ERROR);
//        } else {
//            ModelAndView view = new ModelAndView();
//            view.setViewName("error/403");
//            return view;
//        }
//    }

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity<ApiErrorResponse> globalExceptionHandle(GlobalException e, HttpServletResponse response) {
//        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorResponse errorResponse = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withError(status.name())
                .withMessage(e.getMsg())
                .withDetails(e + e.getMsg())
                .build();

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
