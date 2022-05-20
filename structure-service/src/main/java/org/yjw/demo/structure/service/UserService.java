package org.yjw.demo.structure.service;

import org.yjw.demo.structure.dao.bo.UserBO;
import org.yjw.demo.structure.dao.query.UserQuery;

import java.util.List;

/**
 * @author yjw
 * @date 2022/5/19
 */
public interface UserService {
    UserBO addUser(UserQuery query);

    UserBO getById(Long userId);

    List<UserBO> listUsers(UserQuery query);
}
