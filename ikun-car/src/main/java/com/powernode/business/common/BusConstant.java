package com.powernode.business.common;

/**
 * @Author 徐联理
 * @Date 2023/7/5 15:17
 * @Description
 */
public interface BusConstant {
    /**
     * 出租单状态
     */
    Long RENT_STATUS_NOT_BACK=0L;//未归还
    Long RENT_STATUS_IS_BACK=1L;//已归还

    /**
     * 汽车的出租状态
     */
    Long RENT_STATUS_NOT_RENT=0L;//未出租
    Long RENT_STATUS_IS_RENT=1L;//已出租
    String DEL_0 = "0";
}
