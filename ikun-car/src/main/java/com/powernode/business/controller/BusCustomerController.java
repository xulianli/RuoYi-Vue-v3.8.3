package com.powernode.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.powernode.business.domain.BusCustomer;
import com.powernode.business.service.IBusCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户增删改查Controller
 * 
 * @author ruoyi
 * @date 2023-07-03
 */
@Api(tags = "客户增删改查" )
@RestController
@RequestMapping("/business/customer")
public class BusCustomerController extends BaseController
{
    @Autowired
    private IBusCustomerService busCustomerService;

    /**
     * 查询客户增删改查列表
     */
    @ApiOperation(value = "查询客户增删改查列表")
    @PreAuthorize("@ss.hasPermi('business:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusCustomer busCustomer)
    {
        startPage();
        List<BusCustomer> list = busCustomerService.selectBusCustomerList(busCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户增删改查列表
     */
    @ApiOperation(value = "导出客户增删改查列表")
    @PreAuthorize("@ss.hasPermi('business:customer:export')")
    @Log(title = "客户增删改查", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusCustomer busCustomer)
    {
        List<BusCustomer> list = busCustomerService.selectBusCustomerList(busCustomer);
        ExcelUtil<BusCustomer> util = new ExcelUtil<BusCustomer>(BusCustomer.class);
        util.exportExcel(response, list, "客户增删改查数据");
    }

    /**
     * 获取客户增删改查详细信息
     */
    @ApiOperation(value = "获取客户增删改查详细信息")
    @PreAuthorize("@ss.hasPermi('business:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busCustomerService.selectBusCustomerById(id));
    }

    /**
     * 新增客户增删改查
     */
    @ApiOperation(value = "新增客户增删改查")
    @PreAuthorize("@ss.hasPermi('business:customer:add')")
    @Log(title = "客户增删改查", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusCustomer busCustomer)
    {
        return toAjax(busCustomerService.insertBusCustomer(busCustomer));
    }

    /**
     * 修改客户增删改查
     */
    @ApiOperation(value = "修改客户增删改查")
    @PreAuthorize("@ss.hasPermi('business:customer:edit')")
    @Log(title = "客户增删改查", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusCustomer busCustomer)
    {
        return toAjax(busCustomerService.updateBusCustomer(busCustomer));
    }

    /**
     * 删除客户增删改查
     */
    @ApiOperation(value = "删除客户增删改查")
    @PreAuthorize("@ss.hasPermi('business:customer:remove')")
    @Log(title = "客户增删改查", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busCustomerService.deleteBusCustomerByIds(ids));
    }

    /**
     * 身份证号查询客户
     * @param identity
     * @return
     */
    @GetMapping(value = "/getCustomerByIdentity/{identity}")
    public AjaxResult getCustomerByIdentity(@PathVariable("identity")String identity){
        BusCustomer busCustomer = busCustomerService.selectBusCustomerByIdentity(identity);
        if (null!=busCustomer){
            return AjaxResult.success(busCustomer);
        }else{
            return AjaxResult.error("当前身份证的客户不存在，请核对后查询");
        }
    }

}
