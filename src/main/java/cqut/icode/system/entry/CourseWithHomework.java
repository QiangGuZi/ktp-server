package cqut.icode.system.entry;

import lombok.Data;

import java.util.List;

/**
 * @author tq
 * @date 2019/12/18
 */
@Data
public class CourseWithHomework extends Course {
    private Long homeworkId;
    private List<Long> homeworkIds;
}
