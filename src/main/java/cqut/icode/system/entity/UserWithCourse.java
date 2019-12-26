package cqut.icode.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author tq
 * @date 2019/12/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserWithCourse extends User {
    @Transient
    private Long courseId;
    @Transient
    private List<Long> courseIds;
}
