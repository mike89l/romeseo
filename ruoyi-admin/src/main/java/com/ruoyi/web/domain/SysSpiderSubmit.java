package com.ruoyi.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sys_spider_submit
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
public class SysSpiderSubmit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** url数量 */
    @Excel(name = "url数量")
    private Long urlnum;

    /** url */
    @Excel(name = "url")
    private String url;

    /** 蜘蛛池 */
    @Excel(name = "蜘蛛池")
    private String pool;

    /** 是否强引 */
    @Excel(name = "是否强引")
    private Long forcedBootState;

    /** 所需积分 */
    @Excel(name = "所需积分")
    private Long integral;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUrlnum(Long urlnum) 
    {
        this.urlnum = urlnum;
    }

    public Long getUrlnum() 
    {
        return urlnum;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setPool(String pool) 
    {
        this.pool = pool;
    }

    public String getPool() 
    {
        return pool;
    }
    public void setForcedBootState(Long forcedBootState) 
    {
        this.forcedBootState = forcedBootState;
    }

    public Long getForcedBootState() 
    {
        return forcedBootState;
    }
    public void setIntegral(Long integral) 
    {
        this.integral = integral;
    }

    public Long getIntegral() 
    {
        return integral;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("urlnum", getUrlnum())
            .append("url", getUrl())
            .append("pool", getPool())
            .append("forcedBootState", getForcedBootState())
            .append("integral", getIntegral())
            .toString();
    }
}
