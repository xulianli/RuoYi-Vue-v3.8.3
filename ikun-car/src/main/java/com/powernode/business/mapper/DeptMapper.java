package com.powernode.business.mapper;

import com.powernode.business.domain.SyDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(SyDept record);

    int insertSelective(SyDept record);

    SyDept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(SyDept record);

    int updateByPrimaryKey(SyDept record);

    List<SyDept> selectAllDept();
}