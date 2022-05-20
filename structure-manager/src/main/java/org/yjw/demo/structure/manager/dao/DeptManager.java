package org.yjw.demo.structure.manager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yjw.demo.structure.dao.dataobject.DeptDO;
import org.yjw.demo.structure.dao.mapper.DeptMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yjw
 * @date 2022/5/19
 */
@Service
public class DeptManager {
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 获取简单部门Map
     * key为部门主键ID；value为部门名称
     *
     * @return Map<Integer, String>
     */
    public Map<Integer, String> getSimpleDeptMap() {
        // 查部门
        List<DeptDO> deptDOList = deptMapper.listAll();
        // 转成Map格式
        return deptDOList.stream()
                .collect(Collectors.toMap(DeptDO::getId, DeptDO::getName, (oldKey, newKey) -> newKey));
    }

}
