package com.powernode.business.service;

import com.powernode.business.domain.SyDept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有二级部门
     * @return
     */
    List<SyDept> selectAllDept();
}
