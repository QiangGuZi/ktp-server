package cqut.icode.common.dto;

import lombok.Data;

/**
 * @author tq
 */
@Data
public class LoginItem {
    private String username;
    /** 手机号登陆暂未支持 */
    private String mobile;
    private String password;
    private Boolean rememberMe;
}
