package cqut.icode.common.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tq
 * @date 2019/12/18
 */
@Service
public interface BaseService<T> {
    /**
     * 查找所有
     * @return 所有结果
     */
    List<T> selectAll();

    /**
     * 根据主键查找
     * @param key 主键
     * @return 查找结果
     */
    T selectByKey(Object key);

    /**
     * add
     * @param entity 要增加的实体
     */
    void add(T entity);

    /**
     * 根据主键删除
     * @param key 主键
     */
    void delete(Object key);

    /**
     * 批量删除
     * @param ids 主键列表
     * @param property 主键名，如 id role_id
     * @param clazz 实体类的类
     */
    void batchDelete(List<Long> ids, String property, Class<T> clazz);

    /**
     * 更新全部属性
     * @param entity 待更新的实体
     */
    void updateAll(T entity);

    /**
     * 更新不为null的全部属性
     * @param entity
     */
    void updateNotNull(T entity);

    /**
     * 根据条件查询
     * @param example 创建的条件
     * @return 查询结果
     */
    List<T> selectByExample(Object example);
}
