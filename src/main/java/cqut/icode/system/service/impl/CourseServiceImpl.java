package cqut.icode.system.service.impl;

import cqut.icode.common.service.impl.BaseServiceImpl;
import cqut.icode.system.entity.Course;
import cqut.icode.system.mapper.CourseMapper;
import cqut.icode.system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tq
 * @date 2019/12/18
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Boolean checkCodeIsExist(String code) {
        if (code == null || code.isEmpty()) {
            return false;
        }
        Example example = new Example(Course.class);
        example.createCriteria().andCondition("upper(code)=", code.toUpperCase());
        List<Course> courseList = this.selectByExample(example);

        return courseList.size() == 1;
    }
}
