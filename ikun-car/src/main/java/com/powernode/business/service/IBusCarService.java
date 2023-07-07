package com.powernode.business.service;

import java.util.List;
import com.powernode.business.domain.BusCar;

/**
 * 车辆Service接口
 * 
 * @author powernode
 * @date 2023-07-04
 */
public interface IBusCarService 
{
    /**
     * 查询车辆
     * 
     * @param id 车辆主键
     * @return 车辆
     */
    public BusCar selectBusCarById(Long id);

    /**
     * 查询车辆列表
     * 
     * @param busCar 车辆
     * @return 车辆集合
     */
    public List<BusCar> selectBusCarList(BusCar busCar);

    /**
     * 新增车辆
     * 
     * @param busCar 车辆
     * @return 结果
     */
    public int insertBusCar(BusCar busCar);

    /**
     * 修改车辆
     * 
     * @param busCar 车辆
     * @return 结果
     */
    public int updateBusCar(BusCar busCar);

    /**
     * 批量删除车辆
     * 
     * @param ids 需要删除的车辆主键集合
     * @return 结果
     */
    public int deleteBusCarByIds(Long[] ids);

    /**
     * 删除车辆信息
     * 
     * @param id 车辆主键
     * @return 结果
     */
    public int deleteBusCarById(Long id);

    BusCar queryCarByCarNumber(String carNumber);
}
