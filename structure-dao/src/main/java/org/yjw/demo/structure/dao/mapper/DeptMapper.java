package org.yjw.demo.structure.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yjw.demo.structure.dao.dataobject.DeptDO;

import java.util.List;


/**
 * @author yjw
 * @date 2022/5/18
 */
@Mapper
public interface DeptMapper {
    List<DeptDO> listAll();
}
