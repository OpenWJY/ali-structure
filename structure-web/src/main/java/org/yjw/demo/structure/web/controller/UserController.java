package org.yjw.demo.structure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.yjw.demo.structure.api.model.UserVO;
import org.yjw.demo.structure.bo.UserBO;
import org.yjw.demo.structure.dao.dataobject.UserDO;
import org.yjw.demo.structure.dao.query.UserQuery;
import org.yjw.demo.structure.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final BeanCopier copier = BeanCopier.create(UserBO.class, UserVO.class, false);

    @GetMapping("/{userId}")
    public UserVO addUser(@PathVariable(value = "userId") Long userId) {
        UserBO userBO = userService.getById(userId);
        UserVO vo = new UserVO();
        copier.copy(userBO, vo, null);
        return vo;
    }

    @PostMapping("/list")
    public List<UserVO> listUsers(@RequestBody UserQuery query) {
        // 参数校验

        // 执行逻辑调用
        List<UserBO> userBOList = userService.listUsers(query);

        // 数据转换 todo 使用其它转换工具
        List<UserVO> resultList = userBOList.stream().map(bo -> {
            UserVO vo = new UserVO();
            copier.copy(bo, vo, null);
            return vo;
        }).collect(Collectors.toList());

        // 数据返回
        return resultList;
    }
}
