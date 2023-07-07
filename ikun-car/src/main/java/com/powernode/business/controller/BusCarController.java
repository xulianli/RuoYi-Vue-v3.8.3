package com.powernode.business.controller;

import java.util.Date;
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
import com.powernode.business.domain.BusCar;
import com.powernode.business.service.IBusCarService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆Controller
 * 
 * @author powernode
 * @date 2023-07-04
 */
@Api(tags = "车辆" )
@RestController
@RequestMapping("/business/car")
public class BusCarController extends BaseController
{
    @Autowired
    private IBusCarService busCarService;

    /**
     * 查询车辆列表
     */
    @ApiOperation(value = "查询车辆列表")
    @PreAuthorize("@ss.hasPermi('business:car:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusCar busCar)
    {
        startPage();
        List<BusCar> list = busCarService.selectBusCarList(busCar);
        return getDataTable(list);
    }

    /**
     * 导出车辆列表
     */
    @ApiOperation(value = "导出车辆列表")
    @PreAuthorize("@ss.hasPermi('business:car:export')")
    @Log(title = "车辆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusCar busCar)
    {
        List<BusCar> list = busCarService.selectBusCarList(busCar);
        ExcelUtil<BusCar> util = new ExcelUtil<BusCar>(BusCar.class);
        util.exportExcel(response, list, "车辆数据");
    }

    /**
     * 获取车辆详细信息
     */
    @ApiOperation(value = "获取车辆详细信息")
    @PreAuthorize("@ss.hasPermi('business:car:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busCarService.selectBusCarById(id));
    }

    /**
     * 新增车辆
     */
    @ApiOperation(value = "新增车辆")
    @PreAuthorize("@ss.hasPermi('business:car:add')")
    @Log(title = "车辆", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusCar busCar)
    {
        busCar.setCreateBy(this.getUsername());
        return toAjax(busCarService.insertBusCar(busCar));
    }

    /**
     * 修改车辆
     */
    @ApiOperation(value = "修改车辆")
    @PreAuthorize("@ss.hasPermi('business:car:edit')")
    @Log(title = "车辆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusCar busCar)
    {
        busCar.setUpdateBy(getUsername());
        busCar.setUpdateTime(new Date());
        return toAjax(busCarService.updateBusCar(busCar));
    }

    /**
     * 删除车辆
     */
    @ApiOperation(value = "删除车辆")
    @PreAuthorize("@ss.hasPermi('business:car:remove')")
    @Log(title = "车辆", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busCarService.deleteBusCarByIds(ids));
    }
}
