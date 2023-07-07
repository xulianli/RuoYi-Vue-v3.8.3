package com.powernode.business.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.business.mapper.BusCustomerMapper;
import com.powernode.business.domain.BusCustomer;
import com.powernode.business.service.IBusCustomerService;

/**
 * 客户增删改查Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-03
 */
@Service
public class BusCustomerServiceImpl implements IBusCustomerService 
{
    @Autowired
    private BusCustomerMapper busCustomerMapper;

    /**
     * 查询客户增删改查
     * 
     * @param id 客户增删改查主键
     * @return 客户增删改查
     */
    @Override
    public BusCustomer selectBusCustomerById(Long id)
    {
        return busCustomerMapper.selectBusCustomerById(id);
    }

    /**
     * 查询客户增删改查列表
     * 
     * @param busCustomer 客户增删改查
     * @return 客户增删改查
     */
    @Override
    public List<BusCustomer> selectBusCustomerList(BusCustomer busCustomer)
    {
        return busCustomerMapper.selectBusCustomerList(busCustomer);
    }

    /**
     * 新增客户增删改查
     * 
     * @param busCustomer 客户增删改查
     * @return 结果
     */
    @Override
    public int insertBusCustomer(BusCustomer busCustomer)
    {
        busCustomer.setCreateTime(DateUtils.getNowDate());
        return busCustomerMapper.insertBusCustomer(busCustomer);
    }

    /**
     * 修改客户增删改查
     * 
     * @param busCustomer 客户增删改查
     * @return 结果
     */
    @Override
    public int updateBusCustomer(BusCustomer busCustomer)
    {
        busCustomer.setUpdateTime(DateUtils.getNowDate());
        return busCustomerMapper.updateBusCustomer(busCustomer);
    }

    /**
     * 批量删除客户增删改查
     * 
     * @param ids 需要删除的客户增删改查主键
     * @return 结果
     */
    @Override
    public int deleteBusCustomerByIds(Long[] ids)
    {
        return busCustomerMapper.deleteBusCustomerByIds(ids);
    }

    /**
     * 删除客户增删改查信息
     * 
     * @param id 客户增删改查主键
     * @return 结果
     */
    @Override
    public int deleteBusCustomerById(Long id)
    {
        return busCustomerMapper.deleteBusCustomerById(id);
    }

    @Override
    public BusCustomer selectBusCustomerByIdentity(String identity) {
        return this.busCustomerMapper.selectBusCustomerByIdentity(identity);
    }

    @Override
    public BusCustomer queryCustomerByIdentity(String identity) {
        return this.busCustomerMapper.selectBusCustomerByIdentity(identity);
    }

    @Override
    public AjaxResult getCustomerAddress() {
        List<Map<String,Object>> customerList=this.busCustomerMapper.countCustomerListByAddress();
        if (customerList.isEmpty()){
            return AjaxResult.error("查询失败");
        }
        return AjaxResult.success(customerList);

    }
}
