package org.yjw.demo.structure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import org.yjw.demo.structure.dao.bo.UserBO;
import org.yjw.demo.structure.dao.dataobject.UserDO;
import org.yjw.demo.structure.dao.mapper.UserMapper;
import org.yjw.demo.structure.dao.query.UserQuery;
import org.yjw.demo.structure.manager.DeptManager;
import org.yjw.demo.structure.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptManager deptManager;

    private static final BeanCopier doCopier2Bo = BeanCopier.create(UserDO.class, UserBO.class, false);
    private static final BeanCopier qoCopier2Do = BeanCopier.create(UserQuery.class, UserDO.class, false);


    @Override
    public UserBO addUser(UserQuery query) {
        // 1.数据转型
        UserDO userDO = new UserDO();
        qoCopier2Do.copy(query, userDO, null);

        // 2.调用数据访问层
        userMapper.insert(userDO);

        // 3.组装数据响应
        UserBO userBO = new UserBO();
        doCopier2Bo.copy(userDO, userBO, null);
        return userBO;
    }

    @Override
    public UserBO getById(Long userId) {
        // 1.调用数据访问层
        UserDO userDO = userMapper.getById(userId);

        // 2.调用通用业务处理层
        Map<Integer, String> simpleDeptMap = deptManager.getSimpleDeptMap();

        // 3.组装数据响应
        UserBO bo = new UserBO();
        doCopier2Bo.copy(userDO, bo, null);
        bo.setDeptName(simpleDeptMap.getOrDefault(userDO.getDeptId(), ""));

        // 4.返回
        return bo;
    }

    @Override
    public List<UserBO> listUsers(UserQuery query) {
        // 1.调用数据访问层
        List<UserDO> userDOList = userMapper.listUsers(query);

        // 2.调用通用业务处理层
        Map<Integer, String> simpleDeptMap = deptManager.getSimpleDeptMap();

        // 3.组装数据响应 并 返回
        return userDOList.stream().map(userDO -> {
            UserBO bo = new UserBO();
            doCopier2Bo.copy(userDO, bo, null);
            bo.setDeptName(simpleDeptMap.getOrDefault(userDO.getDeptId(), ""));
            return bo;
        }).collect(Collectors.toList());
    }
}
