package cqut.icode.common.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @author tq
 * @date 2019/12/18
 */
public class GlobalException extends RuntimeException {
    @Getter
    @Setter
    private String msg;

    public GlobalException(String message) {
        this.msg = message;
    }
}
