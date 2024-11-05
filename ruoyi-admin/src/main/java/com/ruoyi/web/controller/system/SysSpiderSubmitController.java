package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.SysSpiderSubmit;
//import com.ruoyi.web.service.ISysSpiderSubmitService;
import com.ruoyi.system.domain.SysSpiderSubmitStutas;
import com.ruoyi.system.service.ISysSpiderSubmitService;
import com.ruoyi.system.service.ISysSpiderSubmitStutasService;
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

    @Autowired
    private ISysSpiderSubmitStutasService sysSpiderSubmitStutasService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('spider:submit:list')")
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
    @PreAuthorize("@ss.hasPermi('spider:submit:export')")
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
    @PreAuthorize("@ss.hasPermi('spider:submit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysSpiderSubmitService.selectSysSpiderSubmitById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('spider:submit:add')")
    @Log(title = "添加链接", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public AjaxResult add(@RequestBody SysSpiderSubmit sysSpiderSubmit)
    {
//        return toAjax(sysSpiderSubmitService.insertSysSpiderSubmit(sysSpiderSubmit));
        // 1. 插入链接
        int result = sysSpiderSubmitService.insertSysSpiderSubmit(sysSpiderSubmit);
        if (result > 0) {
            // 2. 查询最新的链接列表
            List<SysSpiderSubmit> linkList = sysSpiderSubmitService.selectSysSpiderSubmitList(new SysSpiderSubmit());

            // 确保列表中至少有一条链接
            if (linkList.isEmpty()) {
                return AjaxResult.error("没有可用链接");
            }

            // 3. 获取最新一条链接
            SysSpiderSubmit latestLink = linkList.get(linkList.size() - 1);
            // 创建 SimpleDateFormat 实例，指定目标格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 获取当前时间
            String formattedDate = sdf.format(new Date());

            // 4. 构造状态数据
            SysSpiderSubmitStutas stutasData = new SysSpiderSubmitStutas();
            stutasData.setSubmitId(latestLink.getId()); // 使用最新链接的 ID
            stutasData.setSubmitType(sysSpiderSubmit.getPool()); // 使用蜘蛛池作为类型
            stutasData.setSubmitStutsa(0); // 默认值
            stutasData.setUrlnum(sysSpiderSubmit.getUrlnum().toString());
            stutasData.setForcedBootState(latestLink.getForcedBootState().toString());
            stutasData.setTicktime(formattedDate); // 当前时间
//            stutasData.setTicktime(new Date().toISOString().slice(0, 19).replace("T", " "));  // "2024-11-04 15:32:00"


            // 5. 调用状态服务添加状态
            int statusResult = sysSpiderSubmitStutasService.insertSysSpiderSubmitStutas(stutasData);

            if (statusResult > 0) {
                return AjaxResult.success("状态添加成功");
            } else {
                return AjaxResult.error("状态添加失败");
            }
        } else {
            return AjaxResult.error("链接添加失败");
        }
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('spider:submit:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSpiderSubmit sysSpiderSubmit)
    {
        return toAjax(sysSpiderSubmitService.updateSysSpiderSubmit(sysSpiderSubmit));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('spider:submit:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysSpiderSubmitService.deleteSysSpiderSubmitByIds(ids));
    }
}
