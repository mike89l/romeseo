package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Enterprise;

import java.util.List;

public interface EnterpriseMapper {

    List<Enterprise> selectEnterprise(Enterprise enterprise);

    int insertEnterprise(Enterprise enterprise);

    Enterprise selectEnterpriseById(Long enterpriseId);

    int updateEnterprise(Enterprise enterprise);

    int deleteEnterpriseByIds(Long[] enterpriseId);

    String roleid(String userid);

    List<Enterprise> userid(String roleint); //username

    List<Enterprise> username(String username); //username

    List<Enterprise> sselectid(Enterprise enterprise); //
}
