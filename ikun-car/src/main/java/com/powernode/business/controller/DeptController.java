package com.powernode.business.controller;

import com.powernode.business.domain.SyDept;
import com.powernode.business.service.DeptService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 徐联理
 * @Date 2023/7/3 14:53
 * @Description
 */
@RestController
@RequestMapping("/business/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public AjaxResult list()
    {
        List<SyDept> list = deptService.selectAllDept();
        return AjaxResult.success("查询成功",list);
    }



}
