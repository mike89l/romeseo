package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysSpiderSubmit;

import java.util.List;
//import com.ruoyi.system.domain.SysSpiderSubmit;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
public interface SysSpiderSubmitMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysSpiderSubmit selectSysSpiderSubmitById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysSpiderSubmit 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysSpiderSubmit> selectSysSpiderSubmitList(SysSpiderSubmit sysSpiderSubmit);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysSpiderSubmit 【请填写功能名称】
     * @return 结果
     */
    public int insertSysSpiderSubmit(SysSpiderSubmit sysSpiderSubmit);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysSpiderSubmit 【请填写功能名称】
     * @return 结果
     */
    public int updateSysSpiderSubmit(SysSpiderSubmit sysSpiderSubmit);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysSpiderSubmitById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSpiderSubmitByIds(Long[] ids);
}
