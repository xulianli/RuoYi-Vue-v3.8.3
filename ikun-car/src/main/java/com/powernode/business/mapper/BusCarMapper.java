package com.powernode.business.mapper;

import java.util.List;
import com.powernode.business.domain.BusCar;
import org.apache.ibatis.annotations.Param;

/**
 * 车辆Mapper接口
 * 
 * @author powernode
 * @date 2023-07-04
 */
public interface BusCarMapper 
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
     * 删除车辆
     * 
     * @param id 车辆主键
     * @return 结果
     */
    public int deleteBusCarById(Long id);

    /**
     * 批量删除车辆
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusCarByIds(Long[] ids);

    /**
     * 更新车辆状态
     * @param carNumber
     * @param status
     */
    void updateCarRentStatus(@Param("carNumber") String carNumber, @Param("status") Long status);

    BusCar queryCarByCarNumber(@Param("carNumber") String carNumber);
}
