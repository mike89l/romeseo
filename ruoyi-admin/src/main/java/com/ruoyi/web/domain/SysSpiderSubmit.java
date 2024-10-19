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
    private int id;

    /** url数量 */
    @Excel(name = "url数量")
    private int urlnum;

    /** url */
    @Excel(name = "url")
    private String url;

    /** 蜘蛛池 */
    @Excel(name = "蜘蛛池")
    private String pool;

    /** 是否强引 */
    @Excel(name = "是否强引")
    private int forcedBootState;

    /** 所需积分 */
    @Excel(name = "所需积分")
    private int integral;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUrlnum() {
        return urlnum;
    }

    public void setUrlnum(int urlnum) {
        this.urlnum = urlnum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public int getForcedBootState() {
        return forcedBootState;
    }

    public void setForcedBootState(int forcedBootState) {
        this.forcedBootState = forcedBootState;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "SysSpiderSubmit{" +
                "id=" + id +
                ", urlnum=" + urlnum +
                ", url='" + url + '\'' +
                ", pool='" + pool + '\'' +
                ", forcedBootState=" + forcedBootState +
                ", integral=" + integral +
                '}';
    }
}
