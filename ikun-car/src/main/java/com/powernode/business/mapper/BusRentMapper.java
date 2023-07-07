package com.powernode.business.mapper;

import java.util.List;
import com.powernode.business.domain.BusRent;
import org.apache.ibatis.annotations.Param;

/**
 * 出租单Mapper接口
 * 
 * @author powernode
 * @date 2023-07-05
 */
public interface BusRentMapper 
{
    /**
     * 查询出租单
     * 
     * @param id 出租单主键
     * @return 出租单
     */
    public BusRent selectBusRentById(Long id);

    /**
     * 查询出租单列表
     * 
     * @param busRent 出租单
     * @return 出租单集合
     */
    public List<BusRent> selectBusRentList(BusRent busRent);

    /**
     * 新增出租单
     * 
     * @param busRent 出租单
     * @return 结果
     */
    public int insertBusRent(BusRent busRent);

    /**
     * 修改出租单
     * 
     * @param busRent 出租单
     * @return 结果
     */
    public int updateBusRent(BusRent busRent);

    /**
     * 删除出租单
     * 
     * @param id 出租单主键
     * @return 结果
     */
    public int deleteBusRentById(Long id);

    /**
     * 批量删除出租单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusRentByIds(Long[] ids);

    public BusRent queryRentByRentSn(@Param("rentSn") String rentSn);
}
