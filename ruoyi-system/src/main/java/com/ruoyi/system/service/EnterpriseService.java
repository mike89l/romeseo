package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.Enterprise;

import java.util.List;

public interface EnterpriseService {

    //企业列表
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

    //企业新增
    int insertEnterprise(Enterprise enterprise);


    Enterprise selectEnterpriseById(Long enterpriseId);


    int updateEnterprise(Enterprise enterprise);


    int updateEnterpriseStatus(Enterprise enterprise);

    int deleteEnterpriseByIds(Long[] enterpriseId);


}
