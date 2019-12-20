package cqut.icode.common.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tq
 * @date 2019/12/18
 */
@Data
@ToString
public class QueryPage implements Serializable {
    /** 当前页 */
    private int currentPage;
    /** 页数大小 */
    private int pageSize;
}

