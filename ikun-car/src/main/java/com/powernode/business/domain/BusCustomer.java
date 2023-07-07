package com.powernode.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户增删改查对象 bus_customer
 * 
 * @author ruoyi
 * @date 2023-07-03
 */
public class BusCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户表的主键 */
    @Excel(name = "客户表的主键")
    private Long id;

    /** 身份证 */
    @Excel(name = "身份证")
    private String identity;

    /** 姓名 */
    @Excel(name = "姓名")
    private String customerName;

    /** 性别 */
    @Excel(name = "性别")
    private Long sex;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 所属门店名字 */
    @Excel(name = "所属门店名字")
    private String deptName;

    /** 所属门店ID */
    private Long deptId;

    /** 职位 */
    @Excel(name = "职位")
    private String job;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIdentity(String identity) 
    {
        this.identity = identity;
    }

    public String getIdentity() 
    {
        return identity;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("identity", getIdentity())
            .append("customerName", getCustomerName())
            .append("sex", getSex())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("deptName", getDeptName())
            .append("deptId", getDeptId())
            .append("job", getJob())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
