package com.powernode.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出租单对象 bus_rent
 * 
 * @author powernode
 * @date 2023-07-05
 */
public class BusRent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出租单id */
    private Long id;

    /** 出租单号 */
    @Excel(name = "出租单号")
    private String rentSn;

    /** 出租价格 */
    @Excel(name = "出租价格")
    private BigDecimal rentprice;

    /** 起租时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起租时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 还车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "还车时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnDate;

    /** 出租单状态(字典表rent_status) */
    @Excel(name = "出租单状态(字典表rent_status)")
    private Long rentStatus;

    /** 客户身份证号 */
    @Excel(name = "客户身份证号")
    private String identity;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    /** 操作人 */
    @Excel(name = "操作人")
    private String opername;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRentSn(String rentSn) 
    {
        this.rentSn = rentSn;
    }

    public String getRentSn() 
    {
        return rentSn;
    }
    public void setRentprice(BigDecimal rentprice) 
    {
        this.rentprice = rentprice;
    }

    public BigDecimal getRentprice() 
    {
        return rentprice;
    }
    public void setBeginDate(Date beginDate) 
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() 
    {
        return beginDate;
    }
    public void setReturnDate(Date returnDate) 
    {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() 
    {
        return returnDate;
    }
    public void setRentStatus(Long rentStatus) 
    {
        this.rentStatus = rentStatus;
    }

    public Long getRentStatus() 
    {
        return rentStatus;
    }
    public void setIdentity(String identity) 
    {
        this.identity = identity;
    }

    public String getIdentity() 
    {
        return identity;
    }
    public void setCarNumber(String carNumber) 
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber() 
    {
        return carNumber;
    }
    public void setOpername(String opername) 
    {
        this.opername = opername;
    }

    public String getOpername() 
    {
        return opername;
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
            .append("rentSn", getRentSn())
            .append("rentprice", getRentprice())
            .append("beginDate", getBeginDate())
            .append("returnDate", getReturnDate())
            .append("rentStatus", getRentStatus())
            .append("identity", getIdentity())
            .append("carNumber", getCarNumber())
            .append("opername", getOpername())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptId", getDeptId())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .toString();
    }


}
