package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.Enterprise;
import com.ruoyi.common.core.domain.entity.PjtConfig;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.mapper.EnterpriseMapper;
import com.ruoyi.system.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;
import static com.ruoyi.common.utils.SecurityUtils.getUsername;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {


    @Autowired
    private EnterpriseMapper enterpriseMapper;


    @Override
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise) {
        SysRole sysRole = new SysRole();

        List<Enterprise> s1 = enterpriseMapper.selectEnterprise(enterprise);
        Long rol = sysRole.getRoleId();
        //超级管理,普管,开发可以查看所有企业网站
        if(sysRole.getRoleId()==1||sysRole.getRoleId()==2||sysRole.getRoleId()==100){
            for(Enterprise e:s1){
                if(e.getEnterpriseType().equals(PjtConfig.getFour())){
                    e.setEnterpriseType(PjtConfig.getFourValue());
                }else if(e.getEnterpriseType().equals(PjtConfig.getFive())){
                    e.setEnterpriseType(PjtConfig.getFiveValue());
                }else if(e.getEnterpriseType().equals(PjtConfig.getSix())){
                    e.setEnterpriseType(PjtConfig.getSixValue());
                }
            }
            return s1;
        }else {
            //普通用户
            enterprise.setUserId(enterprise.getEnterpriseId());
            List<Enterprise> s2 = enterpriseMapper.selectEnterprise(enterprise);
            return s2;
        }
    }

    @Override
    public int insertEnterprise(Enterprise enterprise) {
        //需要插入创建者ID，用来做无限代理，此功能在后期迭代中加上
        enterprise.setCreateTime(PjtConfig.getDate());
        enterprise.setUpdateTime(PjtConfig.getDate());
        if (enterprise.getStatus()==null||enterprise.getStatus().isEmpty()) {
            enterprise.setStatus(PjtConfig.getZero());
        }
        enterprise.setDelFlag(PjtConfig.getZero());
        enterprise.setUserId(getUserId());
        //此处创建者是用户，代表该网站由用户创建，不可以修改
        enterprise.setUserName(getUsername());
        enterprise.setUpdateName(getUsername());
        return enterpriseMapper.insertEnterprise(enterprise);
    }

    @Override
    public Enterprise selectEnterpriseById(Long enterpriseId) {
        return enterpriseMapper.selectEnterpriseById(enterpriseId);
    }

    @Override
    public int updateEnterprise(Enterprise enterprise) {
        enterprise.setUpdateTime(PjtConfig.getDate());
        enterprise.setUpdateName(getUsername());
        return enterpriseMapper.updateEnterprise(enterprise);
    }

    @Override
    public int updateEnterpriseStatus(Enterprise enterprise) {
        return enterpriseMapper.updateEnterprise(enterprise);
    }

    @Override
    public int deleteEnterpriseByIds(Long[] enterpriseId) {
        return enterpriseMapper.deleteEnterpriseByIds(enterpriseId);
    }


}
