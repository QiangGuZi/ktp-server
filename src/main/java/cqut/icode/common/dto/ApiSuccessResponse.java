package cqut.icode.common.dto;

import cqut.icode.common.enums.StatusEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tq
 * @date 2019/12/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSuccessResponse {
    private Integer code;
    private String msg;
    private Object data;

    public ApiSuccessResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiSuccessResponse(StatusEnums enums) {
        this.code = enums.getCode();
        this.msg = enums.getInfo();
    }

    public ApiSuccessResponse(StatusEnums enums, Object data) {
        this.code = enums.getCode();
        this.msg = enums.getInfo();
        this.data = data;
    }

    public static ApiSuccessResponse success() {
        return new ApiSuccessResponse(StatusEnums.SUCCESS);
    }

    public static ApiSuccessResponse success(Object data) {
        return new ApiSuccessResponse(StatusEnums.SUCCESS, data);
    }
}
