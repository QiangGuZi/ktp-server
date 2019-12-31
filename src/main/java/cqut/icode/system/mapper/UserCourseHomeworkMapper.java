package cqut.icode.system.mapper;

import cqut.icode.common.mapper.MyMapper;
import cqut.icode.system.entity.UserCourseHomework;

import java.util.HashMap;
import java.util.List;

/**
 * @author tq
 */
public interface UserCourseHomeworkMapper extends MyMapper<UserCourseHomework> {

    List<HashMap<String, Object>> getHomeworkList(Long courseId, Long homeworkId);

    void myInsertList(List<UserCourseHomework> userCourseHomework);

    HashMap<String, Object> getHomeworkInfo(Long userId, Long courseId, Long homeworkId, Boolean notIsCourseOwner);
}
