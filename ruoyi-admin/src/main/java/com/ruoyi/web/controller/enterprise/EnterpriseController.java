package com.ruoyi.web.controller.enterprise;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.Enterprise;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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








}
