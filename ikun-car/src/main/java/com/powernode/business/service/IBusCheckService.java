package com.powernode.business.service;

import java.util.List;
import com.powernode.business.domain.BusCheck;

/**
 * 检查单Service接口
 * 
 * @author powernode
 * @date 2023-07-06
 */
public interface IBusCheckService 
{
    /**
     * 查询检查单
     * 
     * @param id 检查单主键
     * @return 检查单
     */
    public BusCheck selectBusCheckById(Long id);

    /**
     * 查询检查单列表
     * 
     * @param busCheck 检查单
     * @return 检查单集合
     */
    public List<BusCheck> selectBusCheckList(BusCheck busCheck);

    /**
     * 新增检查单
     * 
     * @param busCheck 检查单
     * @return 结果
     */
    public int insertBusCheck(BusCheck busCheck);

    /**
     * 修改检查单
     * 
     * @param busCheck 检查单
     * @return 结果
     */
    public int updateBusCheck(BusCheck busCheck);

    /**
     * 批量删除检查单
     * 
     * @param ids 需要删除的检查单主键集合
     * @return 结果
     */
    public int deleteBusCheckByIds(Long[] ids);

    /**
     * 删除检查单信息
     * 
     * @param id 检查单主键
     * @return 结果
     */
    public int deleteBusCheckById(Long id);
}
