package org.yjw.demo.structure.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.yjw.demo.structure.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.yjw.demo.structure.dao.query.UserQuery;

import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Mapper
public interface UserMapper {
    List<UserDO> listUsers(@Param(value = "query") UserQuery query);

    UserDO getByName(String name);

    UserDO getById(Long id);

    Long insert(UserDO userDO);
}
