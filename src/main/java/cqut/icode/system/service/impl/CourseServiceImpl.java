package cqut.icode.system.service.impl;

import cqut.icode.common.service.impl.BaseServiceImpl;
import cqut.icode.system.entry.Course;
import cqut.icode.system.mapper.CourseMapper;
import cqut.icode.system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tq
 * @date 2019/12/18
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;


}
