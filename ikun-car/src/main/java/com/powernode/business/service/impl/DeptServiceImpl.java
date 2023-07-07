package com.powernode.business.service.impl;

import com.powernode.business.domain.SyDept;
import com.powernode.business.mapper.DeptMapper;
import com.powernode.business.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 徐联理
 * @Date 2023/7/3 15:23
 * @Description
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    public List<SyDept> selectAllDept() {
        return this.deptMapper.selectAllDept();
    }
}
