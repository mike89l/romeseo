package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.domain.SysSpiderSubmit;
import com.ruoyi.web.service.ISysSpiderSubmitService;
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
//import com.ruoyi.system.domain.SysSpiderSubmit;
//import com.ruoyi.system.service.ISysSpiderSubmitService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
@RestController
@RequestMapping("/system/submit")
public class SysSpiderSubmitController extends BaseController
{
    @Autowired
    private ISysSpiderSubmitService sysSpiderSubmitService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:submit:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSpiderSubmit sysSpiderSubmit)
    {
        startPage();
        List<SysSpiderSubmit> list = sysSpiderSubmitService.selectSysSpiderSubmitList(sysSpiderSubmit);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:submit:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSpiderSubmit sysSpiderSubmit)
    {
        List<SysSpiderSubmit> list = sysSpiderSubmitService.selectSysSpiderSubmitList(sysSpiderSubmit);
        ExcelUtil<SysSpiderSubmit> util = new ExcelUtil<SysSpiderSubmit>(SysSpiderSubmit.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:submit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysSpiderSubmitService.selectSysSpiderSubmitById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:submit:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSpiderSubmit sysSpiderSubmit)
    {
        return toAjax(sysSpiderSubmitService.insertSysSpiderSubmit(sysSpiderSubmit));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:submit:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSpiderSubmit sysSpiderSubmit)
    {
        return toAjax(sysSpiderSubmitService.updateSysSpiderSubmit(sysSpiderSubmit));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:submit:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysSpiderSubmitService.deleteSysSpiderSubmitByIds(ids));
    }
}
