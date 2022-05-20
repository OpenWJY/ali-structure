package org.yjw.demo.structure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.yjw.demo.structure.api.model.UserVO;
import org.yjw.demo.structure.dao.bo.UserBO;
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
    public UserVO getUSer(@PathVariable(value = "userId") Long userId) {
        // 1. 参数校验

        // 2.调用业务逻辑
        UserBO userBO = userService.getById(userId);

        // 3.数据转换
        UserVO vo = new UserVO();
        copier.copy(userBO, vo, null);

        // 4.数据返回
        return vo;
    }

    @PostMapping("/list")
    public List<UserVO> listUsers(@RequestBody UserQuery query) {
        // 1. 参数校验

        // 2.调用业务逻辑
        List<UserBO> userBOList = userService.listUsers(query);

        // 3.数据转换 todo 使用其它转换工具
        List<UserVO> resultList = userBOList.stream().map(bo -> {
            UserVO vo = new UserVO();
            copier.copy(bo, vo, null);
            return vo;
        }).collect(Collectors.toList());

        // 4.数据返回
        return resultList;
    }

    @PostMapping("/add")
    public UserVO addUser(@RequestBody UserQuery query) {
        // 1. 参数校验

        // 2.调用业务逻辑
        UserBO userBO = userService.addUser(query);

        // 3.数据转换
        UserVO vo = new UserVO();
        copier.copy(userBO, vo, null);

        // 4.数据返回
        return vo;
    }
}
