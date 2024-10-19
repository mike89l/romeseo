package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.domain.SysSpiderSubmitStutas;
import com.ruoyi.web.service.ISysSpiderSubmitStutasService;
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
//import com.ruoyi.system.domain.SysSpiderSubmitStutas;
//import com.ruoyi.system.service.ISysSpiderSubmitStutasService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
@RestController
@RequestMapping("/system/stutas")
public class SysSpiderSubmitStutasController extends BaseController
{
    @Autowired
    private ISysSpiderSubmitStutasService sysSpiderSubmitStutasService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:stutas:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSpiderSubmitStutas sysSpiderSubmitStutas)
    {
        startPage();
        List<SysSpiderSubmitStutas> list = sysSpiderSubmitStutasService.selectSysSpiderSubmitStutasList(sysSpiderSubmitStutas);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:stutas:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSpiderSubmitStutas sysSpiderSubmitStutas)
    {
        List<SysSpiderSubmitStutas> list = sysSpiderSubmitStutasService.selectSysSpiderSubmitStutasList(sysSpiderSubmitStutas);
        ExcelUtil<SysSpiderSubmitStutas> util = new ExcelUtil<SysSpiderSubmitStutas>(SysSpiderSubmitStutas.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stutas:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysSpiderSubmitStutasService.selectSysSpiderSubmitStutasById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:stutas:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSpiderSubmitStutas sysSpiderSubmitStutas)
    {
        return toAjax(sysSpiderSubmitStutasService.insertSysSpiderSubmitStutas(sysSpiderSubmitStutas));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:stutas:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSpiderSubmitStutas sysSpiderSubmitStutas)
    {
        return toAjax(sysSpiderSubmitStutasService.updateSysSpiderSubmitStutas(sysSpiderSubmitStutas));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:stutas:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysSpiderSubmitStutasService.deleteSysSpiderSubmitStutasByIds(ids));
    }
}