package cqut.icode.system.service;

import cqut.icode.common.service.BaseService;
import cqut.icode.system.entity.Course;

/**
 * @author tq
 * @date 2019/12/18
 */
public interface CourseService extends BaseService<Course> {
    /**
     * 检查加课码是否存在
     * @param code 加课码
     */
    Boolean checkCodeIsExist(String code);
}
