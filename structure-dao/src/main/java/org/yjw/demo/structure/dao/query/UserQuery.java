package org.yjw.demo.structure.dao.query;

import lombok.Data;

/**
 * @author yjw
 * @date 2022/5/19
 */
@Data
public class UserQuery {
    private Long id;

    private String name;

    private Integer age;

    private Integer deptId;
}
