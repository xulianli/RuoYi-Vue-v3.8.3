package com.powernode.business.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.powernode.business.common.BusConstant;
import com.powernode.business.utils.SNUtils;
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
import com.powernode.business.domain.BusRent;
import com.powernode.business.service.IBusRentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出租单Controller
 * 
 * @author powernode
 * @date 2023-07-05
 */
@Api(tags = "出租单" )
@RestController
@RequestMapping("/business/rent")
public class BusRentController extends BaseController
{
    @Autowired
    private IBusRentService busRentService;

    @GetMapping("getRentSn")
    public AjaxResult getRentSn(){
        return AjaxResult.success("生成成功", SNUtils.createRentSn());
    }

    /**
     * 查询出租单列表
     */
    @ApiOperation(value = "查询出租单列表")
    @PreAuthorize("@ss.hasPermi('business:rent:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusRent busRent)
    {
        startPage();
        String username = getUsername();
        if (!username.equals("admin")){
            busRent.setDeptId(getLoginUser().getDeptId());
        }
        List<BusRent> list = busRentService.selectBusRentList(busRent);
        return getDataTable(list);
    }

    /**
     * 导出出租单列表
     */
    @ApiOperation(value = "导出出租单列表")
    @PreAuthorize("@ss.hasPermi('business:rent:export')")
    @Log(title = "出租单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusRent busRent)
    {
        String username = getUsername();
        if (!username.equals("admin")){
            busRent.setDeptId(getLoginUser().getDeptId());
        }
        List<BusRent> list = busRentService.selectBusRentList(busRent);
        ExcelUtil<BusRent> util = new ExcelUtil<BusRent>(BusRent.class);
        util.exportExcel(response, list, "出租单数据");
    }

    /**
     * 获取出租单详细信息
     */
    @ApiOperation(value = "获取出租单详细信息")
    @PreAuthorize("@ss.hasPermi('business:rent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busRentService.selectBusRentById(id));
    }

    /**
     * 新增出租单
     */
    @ApiOperation(value = "新增出租单")
//    @PreAuthorize("@ss.hasPermi('business:rent:add')")
    @Log(title = "出租单", businessType = BusinessType.INSERT)
    @PostMapping("/addRent")
    public AjaxResult add(@RequestBody BusRent busRent)
    {
        busRent.setRentStatus(BusConstant.RENT_STATUS_NOT_BACK);
        busRent.setOpername(getUsername());
        busRent.setCreateBy(getUsername());
        busRent.setCreateTime(new Date());
        busRent.setDeptId(getLoginUser().getDeptId());
        return toAjax(busRentService.insertBusRent(busRent));
    }

    /**
     * 修改出租单
     */
    @ApiOperation(value = "修改出租单")
    @PreAuthorize("@ss.hasPermi('business:rent:edit')")
    @Log(title = "出租单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusRent busRent)
    {
        Long id = busRent.getId();
        BusRent rent = this.busRentService.selectBusRentById(id);
        if(rent.getRentStatus().equals(BusConstant.RENT_STATUS_IS_BACK)){
            return AjaxResult.error("【"+rent.getRentSn()+"】车辆已归还，数据无法修改");
        }
        busRent.setUpdateBy(getUsername());
        return toAjax(busRentService.updateBusRent(busRent));
    }

    /**
     * 删除出租单
     */
    @ApiOperation(value = "删除出租单")
    @PreAuthorize("@ss.hasPermi('business:rent:remove')")
    @Log(title = "出租单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        for (Long id : ids) {
            BusRent rent = this.busRentService.selectBusRentById(id);
            if(rent.getRentStatus().equals(BusConstant.RENT_STATUS_IS_BACK)){
                return AjaxResult.error("【"+rent.getRentSn()+"】车辆已归还，数据无法删除");
            }
        }
        return toAjax(busRentService.deleteBusRentByIds(ids));
    }
}
