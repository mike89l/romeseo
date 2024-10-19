package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.Enterprise;
import com.ruoyi.common.core.domain.entity.PjtConfig;
import com.ruoyi.system.mapper.ArticleMapper;
import com.ruoyi.system.mapper.EnterpriseMapper;
import com.ruoyi.system.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {


    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise) {
        List<Enterprise> s = enterpriseMapper.selectEnterprise(enterprise);
        for(Enterprise e:s){
            if(e.getEnterpriseType().equals(PjtConfig.getFour())){
                e.setEnterpriseType(PjtConfig.getFourValue());
            }else if(e.getEnterpriseType().equals(PjtConfig.getFive())){
                e.setEnterpriseType(PjtConfig.getFiveValue());
            }else if(e.getEnterpriseType().equals(PjtConfig.getSix())){
                e.setEnterpriseType(PjtConfig.getSixValue());
            }
        }
        System.out.println("==================="+s);
        return s;
    }


}
