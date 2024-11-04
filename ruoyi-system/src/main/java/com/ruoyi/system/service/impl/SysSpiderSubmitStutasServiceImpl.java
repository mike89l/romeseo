package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.ruoyi.system.domain.SysSpiderSubmitStutas;
//import com.ruoyi.web.mapper.SysSpiderSubmitStutasMapper;
import com.ruoyi.system.mapper.EnterpriseMapper;
import com.ruoyi.system.mapper.SysSpiderSubmitStutasMapper;
//import com.ruoyi.web.service.ISysSpiderSubmitStutasService;
import com.ruoyi.system.service.ISysSpiderSubmitStutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;
//import com.ruoyi.system.mapper.SysSpiderSubmitStutasMapper;
//import com.ruoyi.system.domain.SysSpiderSubmitStutas;
//import com.ruoyi.system.service.ISysSpiderSubmitStutasService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
@Service
public class SysSpiderSubmitStutasServiceImpl implements ISysSpiderSubmitStutasService
{
    @Autowired
    private SysSpiderSubmitStutasMapper sysSpiderSubmitStutasMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysSpiderSubmitStutas selectSysSpiderSubmitStutasById(Long id)
    {
        PageHelper.clearPage();
        return sysSpiderSubmitStutasMapper.selectSysSpiderSubmitStutasById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysSpiderSubmitStutas 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysSpiderSubmitStutas> selectSysSpiderSubmitStutasList(SysSpiderSubmitStutas sysSpiderSubmitStutas)
    {
        PageHelper.clearPage();
        String userid = String.valueOf(getUserId());
        String roleid = enterpriseMapper.roleid(userid);
        int roleint = Integer.parseInt(roleid);
        List<SysSpiderSubmitStutas> list = new ArrayList<>();
        if(roleint ==1||roleint==2||roleint ==100) {
                list = sysSpiderSubmitStutasMapper.submitList(sysSpiderSubmitStutas);
            } else {
            sysSpiderSubmitStutas.setUserid(getUserId().intValue());
                list = sysSpiderSubmitStutasMapper.selectSysSpiderSubmitStutasList(sysSpiderSubmitStutas);

        }
        return list;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysSpiderSubmitStutas 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysSpiderSubmitStutas(SysSpiderSubmitStutas sysSpiderSubmitStutas)
    {
        PageHelper.clearPage();
        String user = String.valueOf(getUserId());
        Integer in = getUserId().intValue();
        sysSpiderSubmitStutas.setUserid(in);
        return sysSpiderSubmitStutasMapper.insertSysSpiderSubmitStutas(sysSpiderSubmitStutas);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysSpiderSubmitStutas 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysSpiderSubmitStutas(SysSpiderSubmitStutas sysSpiderSubmitStutas)
    {
        PageHelper.clearPage();
        return sysSpiderSubmitStutasMapper.updateSysSpiderSubmitStutas(sysSpiderSubmitStutas);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysSpiderSubmitStutasByIds(Long[] ids)
    {
        PageHelper.clearPage();
        return sysSpiderSubmitStutasMapper.deleteSysSpiderSubmitStutasByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysSpiderSubmitStutasById(Long id)
    {
        PageHelper.clearPage();
        return sysSpiderSubmitStutasMapper.deleteSysSpiderSubmitStutasById(id);
    }
}
