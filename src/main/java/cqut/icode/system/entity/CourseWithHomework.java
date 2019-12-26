package cqut.icode.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author tq
 * @date 2019/12/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseWithHomework extends Course {
    private Long homeworkId;
    private List<Long> homeworkIds;
}
