package cqut.icode.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tq
 * @date 2019/12/18
 */
public enum StatusEnums {
    /** 成功的请求 status 都为1，和全局处理双重保护 */
    SUCCESS(1, "操作成功"),
    ACCOUNT_UNKNOWN(-1, "账户不存在");

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String info;

    StatusEnums(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
