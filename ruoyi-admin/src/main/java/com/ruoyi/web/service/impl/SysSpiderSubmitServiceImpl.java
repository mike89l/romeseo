package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.web.domain.SysSpiderSubmit;
import com.ruoyi.web.mapper.SysSpiderSubmitMapper;
import com.ruoyi.web.service.ISysSpiderSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.ruoyi.system.mapper.SysSpiderSubmitMapper;
//import com.ruoyi.system.domain.SysSpiderSubmit;
//import com.ruoyi.system.service.ISysSpiderSubmitService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
@Service
public class SysSpiderSubmitServiceImpl implements ISysSpiderSubmitService
{
    @Autowired
    private SysSpiderSubmitMapper sysSpiderSubmitMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysSpiderSubmit selectSysSpiderSubmitById(Long id)
    {
        return sysSpiderSubmitMapper.selectSysSpiderSubmitById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysSpiderSubmit 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysSpiderSubmit> selectSysSpiderSubmitList(SysSpiderSubmit sysSpiderSubmit)
    {
        return sysSpiderSubmitMapper.selectSysSpiderSubmitList(sysSpiderSubmit);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysSpiderSubmit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysSpiderSubmit(SysSpiderSubmit sysSpiderSubmit)
    {
        return sysSpiderSubmitMapper.insertSysSpiderSubmit(sysSpiderSubmit);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysSpiderSubmit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysSpiderSubmit(SysSpiderSubmit sysSpiderSubmit)
    {
        return sysSpiderSubmitMapper.updateSysSpiderSubmit(sysSpiderSubmit);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysSpiderSubmitByIds(Long[] ids)
    {
        return sysSpiderSubmitMapper.deleteSysSpiderSubmitByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysSpiderSubmitById(Long id)
    {
        return sysSpiderSubmitMapper.deleteSysSpiderSubmitById(id);
    }
}
