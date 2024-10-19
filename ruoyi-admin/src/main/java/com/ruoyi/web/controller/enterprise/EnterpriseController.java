package com.ruoyi.web.controller.enterprise;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.domain.entity.Enterprise;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/enterprise/manage")
public class EnterpriseController extends BaseController {


    @Autowired
    private EnterpriseService enterpriseService;


    @PreAuthorize("@ss.hasPermi('enterprise:manage:list')")
    @Log(title = "企业管理-企业列表", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo getEnterpriseList(Enterprise enterprise){
        startPage();
        List<Enterprise> rs = enterpriseService.selectEnterpriseList(enterprise);
        return getDataTable(rs);
    }

    /**
     * 根据企业编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('article:manage:query')")
    @GetMapping(value = "/{enterpriseId}")
    public AjaxResult getInfo(@PathVariable Long enterpriseId)
    {
        Enterprise s = enterpriseService.selectEnterpriseById(enterpriseId);
        return success(s);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:manage:add')")
    @Log(title = "企业管理-企业新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult list(@Validated @RequestBody Enterprise enterprise)
    {
        List<Enterprise> rs = enterpriseService.selectEnterpriseList(enterprise);
        if (rs!=null && rs.size()>0) {
            return error("新增失败，该企业已存在");
        }
        return toAjax(enterpriseService.insertEnterprise(enterprise));
    }

    @PreAuthorize("@ss.hasPermi('enterprise:manage:edit')")
    @Log(title = "企业管理-企业修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Enterprise enterprise)
    {
        return toAjax(enterpriseService.updateEnterprise(enterprise));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('enterprise:manage:edit')")
    @Log(title = "企业管理-企业状态修改", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody Enterprise enterprise)
    {
        enterprise.getStatus();
        return toAjax(enterpriseService.updateEnterpriseStatus(enterprise));
    }


    /**
     * 删除企业
     */
    @PreAuthorize("@ss.hasPermi('enterprise:manage:remove')")
    @Log(title = "企业管理-企业删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{enterpriseId}")
    public AjaxResult remove(@PathVariable Long[] enterpriseId)
    {
//        if (ArrayUtils.contains(articleIds, getUserId()))
//        {
//            return error("当前用户不能删除");
//        }
        return toAjax(enterpriseService.deleteEnterpriseByIds(enterpriseId));
    }









}
