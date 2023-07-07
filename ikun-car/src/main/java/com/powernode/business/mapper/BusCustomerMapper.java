package com.powernode.business.mapper;

import java.util.List;
import java.util.Map;

import com.powernode.business.domain.BusCustomer;
import org.apache.ibatis.annotations.Param;

/**
 * 客户增删改查Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-03
 */
public interface BusCustomerMapper 
{
    /**
     * 查询客户增删改查
     * 
     * @param id 客户增删改查主键
     * @return 客户增删改查
     */
    public BusCustomer selectBusCustomerById(Long id);

    /**
     * 查询客户增删改查列表
     * 
     * @param busCustomer 客户增删改查
     * @return 客户增删改查集合
     */
    public List<BusCustomer> selectBusCustomerList(BusCustomer busCustomer);

    /**
     * 新增客户增删改查
     * 
     * @param busCustomer 客户增删改查
     * @return 结果
     */
    public int insertBusCustomer(BusCustomer busCustomer);

    /**
     * 修改客户增删改查
     * 
     * @param busCustomer 客户增删改查
     * @return 结果
     */
    public int updateBusCustomer(BusCustomer busCustomer);

    /**
     * 删除客户增删改查
     * 
     * @param id 客户增删改查主键
     * @return 结果
     */
    public int deleteBusCustomerById(Long id);

    /**
     * 批量删除客户增删改查
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusCustomerByIds(Long[] ids);

    BusCustomer selectBusCustomerByIdentity(@Param("identity") String identity);

    List<Map<String, Object>> countCustomerListByAddress();
}
