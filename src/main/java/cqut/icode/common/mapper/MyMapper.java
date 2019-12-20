package cqut.icode.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author tq
 * @date 2019/12/18
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
