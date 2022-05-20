package org.yjw.demo.structure.api;


import org.yjw.demo.structure.api.model.UserVO;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface UserService {

    String getUserName(Long id);

    UserVO addUser(UserVO user);
}
