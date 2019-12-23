package cqut.icode.common.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tq
 */
@Data
@ToString
public class Tree<T> {
    /** 节点ID */
    private Long id;
    /** 父节点ID */
    private Long parentId;
    /** 是否有子节点 */
    private Boolean hasChildren;
    /** 是否有父节点 */
    private Boolean hasParent;
    /** 节点名称 */
    private String name;
    /** 节点URL */
    private String url;
    /** 图标 */
    private String icon;
    /** 子节点信息 */
    private List<Tree<T>> children = new ArrayList<>();
}
