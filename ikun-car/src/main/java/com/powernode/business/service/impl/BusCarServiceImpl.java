package com.powernode.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.business.mapper.BusCarMapper;
import com.powernode.business.domain.BusCar;
import com.powernode.business.service.IBusCarService;

/**
 * 车辆Service业务层处理
 * 
 * @author powernode
 * @date 2023-07-04
 */
@Service
public class BusCarServiceImpl implements IBusCarService 
{
    @Autowired
    private BusCarMapper busCarMapper;

    /**
     * 查询车辆
     * 
     * @param id 车辆主键
     * @return 车辆
     */
    @Override
    public BusCar selectBusCarById(Long id)
    {
        return busCarMapper.selectBusCarById(id);
    }

    /**
     * 查询车辆列表
     * 
     * @param busCar 车辆
     * @return 车辆
     */
    @Override
    public List<BusCar> selectBusCarList(BusCar busCar)
    {
        return busCarMapper.selectBusCarList(busCar);
    }

    /**
     * 新增车辆
     * 
     * @param busCar 车辆
     * @return 结果
     */
    @Override
    public int insertBusCar(BusCar busCar)
    {
        busCar.setCreateTime(DateUtils.getNowDate());
        return busCarMapper.insertBusCar(busCar);
    }

    /**
     * 修改车辆
     * 
     * @param busCar 车辆
     * @return 结果
     */
    @Override
    public int updateBusCar(BusCar busCar)
    {
        busCar.setUpdateTime(DateUtils.getNowDate());
        return busCarMapper.updateBusCar(busCar);
    }

    /**
     * 批量删除车辆
     * 
     * @param ids 需要删除的车辆主键
     * @return 结果
     */
    @Override
    public int deleteBusCarByIds(Long[] ids)
    {
        return busCarMapper.deleteBusCarByIds(ids);
    }

    /**
     * 删除车辆信息
     * 
     * @param id 车辆主键
     * @return 结果
     */
    @Override
    public int deleteBusCarById(Long id)
    {
        return busCarMapper.deleteBusCarById(id);
    }

    @Override
    public BusCar queryCarByCarNumber(String carNumber) {
        return this.busCarMapper.queryCarByCarNumber(carNumber);
    }
}
