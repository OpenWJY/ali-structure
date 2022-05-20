package org.yjw.demo.structure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import org.yjw.demo.structure.api.model.UserVO;
import org.yjw.demo.structure.bo.UserBO;
import org.yjw.demo.structure.dao.dataobject.DeptDO;
import org.yjw.demo.structure.dao.dataobject.UserDO;
import org.yjw.demo.structure.dao.mapper.DeptMapper;
import org.yjw.demo.structure.dao.mapper.UserMapper;
import org.yjw.demo.structure.dao.query.UserQuery;
import org.yjw.demo.structure.manager.DeptManager;
import org.yjw.demo.structure.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptManager deptManager;

    private static final BeanCopier copier = BeanCopier.create(UserDO.class, UserBO.class, false);

    @Override
    public UserBO getById(Long userId) {
        // 获取部门map
        Map<Integer, String> simpleDeptMap = deptManager.getSimpleDeptMap();
        // 查人员
        UserDO userDO = userMapper.getById(userId);
        // 组装
        UserBO bo = new UserBO();
        copier.copy(userDO, bo, null);
        bo.setDeptName(simpleDeptMap.getOrDefault(userDO.getDeptId(), ""));
        return bo;
    }

    @Override
    public List<UserBO> listUsers(UserQuery query) {
        // 获取部门map
        Map<Integer, String> simpleDeptMap = deptManager.getSimpleDeptMap();
        // 查人员
        List<UserDO> userDOList = userMapper.listUsers(query);
        // 组装
        List<UserBO> UserBoList = userDOList.stream().map(userDO -> {
            UserBO bo = new UserBO();
            copier.copy(userDO, bo, null);
            bo.setDeptName(simpleDeptMap.getOrDefault(userDO.getDeptId(), ""));
            return bo;
        }).collect(Collectors.toList());

        return UserBoList;
    }
}
