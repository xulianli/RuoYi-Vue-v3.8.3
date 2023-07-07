package com.powernode.business.service;

import java.util.List;
import com.powernode.business.domain.BusCustomer;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 客户增删改查Service接口
 * 
 * @author ruoyi
 * @date 2023-07-03
 */
public interface IBusCustomerService 
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
     * 批量删除客户增删改查
     * 
     * @param ids 需要删除的客户增删改查主键集合
     * @return 结果
     */
    public int deleteBusCustomerByIds(Long[] ids);

    /**
     * 删除客户增删改查信息
     * 
     * @param id 客户增删改查主键
     * @return 结果
     */
    public int deleteBusCustomerById(Long id);

    BusCustomer selectBusCustomerByIdentity(String identity);

    BusCustomer queryCustomerByIdentity(String identity);

    AjaxResult getCustomerAddress();
}
