package com.powernode.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆对象 bus_car
 * 
 * @author powernode
 * @date 2023-07-04
 */
public class BusCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车辆id */
    private Long id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNumber;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 购买价格 */
    @Excel(name = "购买价格")
    private BigDecimal price;

    /** 出租价格 */
    @Excel(name = "出租价格")
    private BigDecimal rentprice;

    /** 出租押金 */
    @Excel(name = "出租押金")
    private Long deposit;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 状态【字典表car_rent_status】 */
    @Excel(name = "状态【字典表car_rent_status】")
    private Long status;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String deptName;

    /** 车辆所属门店 */
    private Long deptId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    private BigDecimal minRentprice;

    private BigDecimal maxRentprice;

    public BigDecimal getMinRentprice() {
        return minRentprice;
    }

    public void setMinRentprice(BigDecimal minRentprice) {
        this.minRentprice = minRentprice;
    }

    public BigDecimal getMaxRentprice() {
        return maxRentprice;
    }

    public void setMaxRentprice(BigDecimal maxRentprice) {
        this.maxRentprice = maxRentprice;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCarNumber(String carNumber) 
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber() 
    {
        return carNumber;
    }
    public void setFrameNumber(String frameNumber) 
    {
        this.frameNumber = frameNumber;
    }

    public String getFrameNumber() 
    {
        return frameNumber;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setRentprice(BigDecimal rentprice) 
    {
        this.rentprice = rentprice;
    }

    public BigDecimal getRentprice() 
    {
        return rentprice;
    }
    public void setDeposit(Long deposit) 
    {
        this.deposit = deposit;
    }

    public Long getDeposit() 
    {
        return deposit;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("carNumber", getCarNumber())
            .append("frameNumber", getFrameNumber())
            .append("brand", getBrand())
            .append("type", getType())
            .append("color", getColor())
            .append("price", getPrice())
            .append("rentprice", getRentprice())
            .append("deposit", getDeposit())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("deptName", getDeptName())
            .append("deptId", getDeptId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
