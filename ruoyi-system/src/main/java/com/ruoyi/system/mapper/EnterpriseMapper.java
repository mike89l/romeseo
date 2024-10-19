package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Enterprise;

import java.util.List;

public interface EnterpriseMapper {

    List<Enterprise> selectEnterprise(Enterprise enterprise);

    int insertEnterprise(Enterprise enterprise);

    Enterprise selectEnterpriseById(Long enterpriseId);

    int updateEnterprise(Enterprise enterprise);

    int deleteEnterpriseByIds(Long[] enterpriseId);
}
