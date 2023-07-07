package com.powernode.business.controller;

import com.powernode.business.service.IBusCustomerService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 徐联理
 * @Date 2023/7/7 11:16
 * @Description
 */
@RestController
@RequestMapping("/business/statistics")
public class EchartController {

    @Autowired
    private IBusCustomerService busCustomerService;

    @GetMapping("/getCustomerAddress")
    public AjaxResult getCustomerAddress(){
        return this.busCustomerService.getCustomerAddress();
    }
}
