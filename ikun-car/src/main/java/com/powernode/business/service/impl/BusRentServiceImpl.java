package com.powernode.business.service.impl;

import java.util.List;

import com.powernode.business.common.BusConstant;
import com.powernode.business.mapper.BusCarMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.business.mapper.BusRentMapper;
import com.powernode.business.domain.BusRent;
import com.powernode.business.service.IBusRentService;

/**
 * 出租单Service业务层处理
 * 
 * @author powernode
 * @date 2023-07-05
 */
@Service
public class BusRentServiceImpl implements IBusRentService 
{
    @Autowired
    private BusRentMapper busRentMapper;
    @Autowired
    private BusCarMapper carMapper;

    /**
     * 查询出租单
     * 
     * @param id 出租单主键
     * @return 出租单
     */
    @Override
    public BusRent selectBusRentById(Long id)
    {
        return busRentMapper.selectBusRentById(id);
    }

    /**
     * 查询出租单列表
     * 
     * @param busRent 出租单
     * @return 出租单
     */
    @Override
    public List<BusRent> selectBusRentList(BusRent busRent)
    {
        return busRentMapper.selectBusRentList(busRent);
    }

    /**
     * 新增出租单
     * 
     * @param busRent 出租单
     * @return 结果
     */
    @Override
    public int insertBusRent(BusRent busRent)
    {
        busRent.setCreateTime(DateUtils.getNowDate());
        this.carMapper.updateCarRentStatus(busRent.getCarNumber(), BusConstant.RENT_STATUS_IS_RENT);
        return busRentMapper.insertBusRent(busRent);
    }

    /**
     * 修改出租单
     * 
     * @param busRent 出租单
     * @return 结果
     */
    @Override
    public int updateBusRent(BusRent busRent)
    {
        busRent.setUpdateTime(DateUtils.getNowDate());
        return busRentMapper.updateBusRent(busRent);
    }

    /**
     * 批量删除出租单
     * 
     * @param ids 需要删除的出租单主键
     * @return 结果
     */
    @Override
    public int deleteBusRentByIds(Long[] ids)
    {
        for (Long id : ids) {
            BusRent rent = this.busRentMapper.selectBusRentById(id);
            carMapper.updateCarRentStatus(rent.getCarNumber(),BusConstant.RENT_STATUS_NOT_RENT);
        }
        return busRentMapper.deleteBusRentByIds(ids);
    }

    /**
     * 删除出租单信息
     * 
     * @param id 出租单主键
     * @return 结果
     */
    @Override
    public int deleteBusRentById(Long id)
    {
        return busRentMapper.deleteBusRentById(id);
    }

    @Override
    public BusRent queryRentByRentSn(String rentSn) {
        return this.busRentMapper.queryRentByRentSn(rentSn);
    }
}
