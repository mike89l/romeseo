package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * SysSpiderSubmit对象
 *
 * @author ruoyi
 * @date 2024-10-13
 */
public class SysSpiderSubmit extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;

    /** url数量 */
    @Excel(name = "url数量")
    private Integer urlnum;

    /** url */
    @Excel(name = "url")
    private String url;

    /** 蜘蛛池 */
    @Excel(name = "蜘蛛池")
    private String pool;

    /** 是否强引 */
    @Excel(name = "是否强引")
    private Integer forcedBootState;

    /** 所需积分 */
    @Excel(name = "所需积分")
    private Integer integral;

    public SysSpiderSubmit() {
    }

    public SysSpiderSubmit(Integer id, Integer urlnum, String url, String pool, Integer forcedBootState, Integer integral) {
        this.id = id;
        this.urlnum = urlnum;
        this.url = url;
        this.pool = pool;
        this.forcedBootState = forcedBootState;
        this.integral = integral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUrlnum() {
        return urlnum;
    }

    public void setUrlnum(Integer urlnum) {
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

    public Integer getForcedBootState() {
        return forcedBootState;
    }

    public void setForcedBootState(Integer forcedBootState) {
        this.forcedBootState = forcedBootState;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
