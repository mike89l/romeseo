package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.web.domain.SysSpiderSubmitStutas;
import com.ruoyi.web.mapper.SysSpiderSubmitStutasMapper;
import com.ruoyi.web.service.ISysSpiderSubmitStutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysSpiderSubmitStutas selectSysSpiderSubmitStutasById(Long id)
    {
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
        return sysSpiderSubmitStutasMapper.selectSysSpiderSubmitStutasList(sysSpiderSubmitStutas);
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
        return sysSpiderSubmitStutasMapper.deleteSysSpiderSubmitStutasById(id);
    }
}
