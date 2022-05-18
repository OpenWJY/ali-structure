package org.yjw.demo.structure.dao.mapper;

import org.yjw.demo.structure.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Mapper
public interface UserMapper {
    List<UserDO> listAll();

    UserDO getByName(String name);

    UserDO getById(Long id);

    Long insert(UserDO userDO);
}
