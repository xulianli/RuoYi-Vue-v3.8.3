package com.powernode.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.powernode.business.common.BusConstant;
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
import com.powernode.business.domain.BusCheck;
import com.powernode.business.service.IBusCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检查单Controller
 * 
 * @author powernode
 * @date 2023-07-06
 */
@Api(tags = "检查单" )
@RestController
@RequestMapping("/business/check")
public class BusCheckController extends BaseController
{
    @Autowired
    private IBusCheckService busCheckService;

    /**
     * 查询检查单列表
     */
    @ApiOperation(value = "查询检查单列表")
    @PreAuthorize("@ss.hasPermi('business:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusCheck busCheck)
    {
        startPage();
        String username = getUsername();
        if (!username.equals("admin")){
            busCheck.setDeptId(getLoginUser().getDeptId());
        }
        List<BusCheck> list = busCheckService.selectBusCheckList(busCheck);
        return getDataTable(list);
    }

    /**
     * 导出检查单列表
     */
    @ApiOperation(value = "导出检查单列表")
    @PreAuthorize("@ss.hasPermi('business:check:export')")
    @Log(title = "检查单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusCheck busCheck)
    {
        List<BusCheck> list = busCheckService.selectBusCheckList(busCheck);
        ExcelUtil<BusCheck> util = new ExcelUtil<BusCheck>(BusCheck.class);
        util.exportExcel(response, list, "检查单数据");
    }

    /**
     * 获取检查单详细信息
     */
    @ApiOperation(value = "获取检查单详细信息")
    @PreAuthorize("@ss.hasPermi('business:check:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busCheckService.selectBusCheckById(id));
    }

    /**
     * 新增检查单
     */
    @ApiOperation(value = "新增检查单")
    @PreAuthorize("@ss.hasPermi('business:check:add')")
    @Log(title = "检查单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusCheck busCheck)
    {
        busCheck.setDelFlag(BusConstant.DEL_0);
        busCheck.setCreateBy(getUsername());
        busCheck.setDeptId(getLoginUser().getDeptId());
        return toAjax(busCheckService.insertBusCheck(busCheck));
    }

    /**
     * 修改检查单
     */
    @ApiOperation(value = "修改检查单")
    @PreAuthorize("@ss.hasPermi('business:check:edit')")
    @Log(title = "检查单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusCheck busCheck)
    {
        return toAjax(busCheckService.updateBusCheck(busCheck));
    }

    /**
     * 删除检查单
     */
    @ApiOperation(value = "删除检查单")
    @PreAuthorize("@ss.hasPermi('business:check:remove')")
    @Log(title = "检查单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busCheckService.deleteBusCheckByIds(ids));
    }
}
