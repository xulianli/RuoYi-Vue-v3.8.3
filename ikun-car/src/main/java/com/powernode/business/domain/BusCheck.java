package com.powernode.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检查单对象 bus_check
 * 
 * @author powernode
 * @date 2023-07-06
 */
public class BusCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查单id */
    private Long id;

    /** 检查单号 */
    @Excel(name = "检查单号")
    private String checkSn;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 描述 */
    @Excel(name = "描述")
    private String checkDesc;

    /** 存在问题 */
    @Excel(name = "存在问题")
    private String problem;

    /** 赔付金额 */
    @Excel(name = "赔付金额")
    private Long payMoney;

    /** 操作员 */
    @Excel(name = "操作员")
    private String opername;

    /** 出租单号 */
    @Excel(name = "出租单号")
    private String rentSn;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCheckSn(String checkSn) 
    {
        this.checkSn = checkSn;
    }

    public String getCheckSn() 
    {
        return checkSn;
    }
    public void setCheckDate(Date checkDate) 
    {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() 
    {
        return checkDate;
    }
    public void setCheckDesc(String checkDesc) 
    {
        this.checkDesc = checkDesc;
    }

    public String getCheckDesc() 
    {
        return checkDesc;
    }
    public void setProblem(String problem) 
    {
        this.problem = problem;
    }

    public String getProblem() 
    {
        return problem;
    }
    public void setPayMoney(Long payMoney) 
    {
        this.payMoney = payMoney;
    }

    public Long getPayMoney() 
    {
        return payMoney;
    }
    public void setOpername(String opername) 
    {
        this.opername = opername;
    }

    public String getOpername() 
    {
        return opername;
    }
    public void setRentSn(String rentSn) 
    {
        this.rentSn = rentSn;
    }

    public String getRentSn() 
    {
        return rentSn;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkSn", getCheckSn())
            .append("checkDate", getCheckDate())
            .append("checkDesc", getCheckDesc())
            .append("problem", getProblem())
            .append("payMoney", getPayMoney())
            .append("opername", getOpername())
            .append("rentSn", getRentSn())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("deptId", getDeptId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
