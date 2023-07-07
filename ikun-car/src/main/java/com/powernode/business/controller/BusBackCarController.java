package com.powernode.business.controller;

import com.powernode.business.common.BusConstant;
import com.powernode.business.domain.BusCar;
import com.powernode.business.domain.BusCustomer;
import com.powernode.business.domain.BusRent;
import com.powernode.business.service.IBusCarService;
import com.powernode.business.service.IBusCustomerService;
import com.powernode.business.service.IBusRentService;
import com.powernode.business.utils.SNUtils;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 徐联理
 * @Date 2023/7/7 8:26
 * @Description
 */
@RestController
@RequestMapping("/business/backCar")
public class BusBackCarController extends BaseController {
    @Resource
    private IBusRentService rentService;

    @Resource
    private IBusCarService carService;

    @Resource
    private IBusCustomerService customerService;

    /**
     * 根据出租单号查询
     * 出租单信息
     * 封装检查单信息
     * 客户信息
     * 车辆信息
     * data:{rent:{},customer:{},car:{},check:{}}
     */
    @GetMapping("/getRentDataByRentId/{rentSn}")
    public AjaxResult getRentDataByRentId(@PathVariable("rentSn") String rentSn){
        //根据rentSn查询出租单位数据
        BusRent rent=rentService.queryRentByRentSn(rentSn);
        //判断rent是否为空
        if(null==rent){
            return AjaxResult.error("【"+rentSn+"】出租单号不存在，请核对后再查询");
        }else{
            if(rent.getRentStatus().equals(BusConstant.RENT_STATUS_IS_BACK)){
                return AjaxResult.error("【"+rentSn+"】出租单号对应的车辆已归还");
            }else{
                //创建一个要返回的map
                Map<String,Object> data=new HashMap<>();
                data.put("rent",rent);
                //根据车牌号查询车辆信息
                BusCar car=this.carService.queryCarByCarNumber(rent.getCarNumber());
                //根据客户身份证号查询客户数据
                BusCustomer customer=this.customerService.queryCustomerByIdentity(rent.getIdentity());
                data.put("car",car);
                data.put("customer",customer);
                //检查单数据
                Map<String,Object> check=new HashMap<>();
                check.put("rentSn",rentSn);
                check.put("checkSn", SNUtils.createCheckSn());
                check.put("problem","");
                check.put("payMoney",0);
                check.put("checkDesc","");
                check.put("opername",getUsername());
                data.put("check",check);
                return AjaxResult.success("查询成功",data);
            }
        }
    }
}
