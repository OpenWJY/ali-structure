package org.yjw.demo.structure.bo;

import lombok.Data;

/**
 * @author yjw
 * @date 2022/5/19
 */
@Data
public class UserBO {
    private Long id;

    private String name;

    private Integer age;

    private Integer deptId;

    private String deptName;
}
