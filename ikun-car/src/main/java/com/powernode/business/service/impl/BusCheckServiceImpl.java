package com.powernode.business.service.impl;

import java.util.List;

import com.powernode.business.common.BusConstant;
import com.powernode.business.domain.BusRent;
import com.powernode.business.mapper.BusCarMapper;
import com.powernode.business.mapper.BusRentMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.business.mapper.BusCheckMapper;
import com.powernode.business.domain.BusCheck;
import com.powernode.business.service.IBusCheckService;

/**
 * 检查单Service业务层处理
 * 
 * @author powernode
 * @date 2023-07-06
 */
@Service
public class BusCheckServiceImpl implements IBusCheckService 
{
    @Autowired
    private BusCheckMapper busCheckMapper;

    @Autowired
    private BusRentMapper rentMapper;

    @Autowired
    private BusCarMapper carMapper;

    /**
     * 查询检查单
     * 
     * @param id 检查单主键
     * @return 检查单
     */
    @Override
    public BusCheck selectBusCheckById(Long id)
    {
        return busCheckMapper.selectBusCheckById(id);
    }

    /**
     * 查询检查单列表
     * 
     * @param busCheck 检查单
     * @return 检查单
     */
    @Override
    public List<BusCheck> selectBusCheckList(BusCheck busCheck)
    {
        return busCheckMapper.selectBusCheckList(busCheck);
    }

    /**
     * 新增检查单
     * 
     * @param busCheck 检查单
     * @return 结果
     */
    @Override
    public int insertBusCheck(BusCheck busCheck)
    {
        busCheck.setCreateTime(DateUtils.getNowDate());
        int i = busCheckMapper.insertBusCheck(busCheck);
        BusRent rent = this.rentMapper.queryRentByRentSn(busCheck.getRentSn());
        rent.setRentStatus(BusConstant.RENT_STATUS_IS_BACK);
        this.rentMapper.updateBusRent(rent);
        this.carMapper.updateCarRentStatus(rent.getCarNumber(),BusConstant.RENT_STATUS_NOT_RENT);
        return i;
    }

    /**
     * 修改检查单
     * 
     * @param busCheck 检查单
     * @return 结果
     */
    @Override
    public int updateBusCheck(BusCheck busCheck)
    {
        busCheck.setUpdateTime(DateUtils.getNowDate());
        return busCheckMapper.updateBusCheck(busCheck);
    }

    /**
     * 批量删除检查单
     * 
     * @param ids 需要删除的检查单主键
     * @return 结果
     */
    @Override
    public int deleteBusCheckByIds(Long[] ids)
    {
        return busCheckMapper.deleteBusCheckByIds(ids);
    }

    /**
     * 删除检查单信息
     * 
     * @param id 检查单主键
     * @return 结果
     */
    @Override
    public int deleteBusCheckById(Long id)
    {
        return busCheckMapper.deleteBusCheckById(id);
    }
}
