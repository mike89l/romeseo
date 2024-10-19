package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sys_spider_submit_stutas
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
public class SysSpiderSubmitStutas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** url提交id */
    @Excel(name = "url提交id")
    private Integer submitId;

    /** url选中的池子 */
    @Excel(name = "url选中的池子")
    private String submitType;

    /** 表单状态 */
    @Excel(name = "表单状态")
    private Integer submitStutsa;

    /** 备注 */
    @Excel(name = "备注")
    private String coment;

    @Excel(name = "链接数量")
    private String urlnum;

    @Excel(name = "是否强引")
    private String forcedBootState;

    @Excel(name = "时间")
    private String ticktime;

    @Override
    public String toString() {
        return "SysSpiderSubmitStutas{" +
                "id=" + id +
                ", submitId=" + submitId +
                ", submitType='" + submitType + '\'' +
                ", submitStutsa=" + submitStutsa +
                ", coment='" + coment + '\'' +
                ", urlnum='" + urlnum + '\'' +
                ", forcedBootState='" + forcedBootState + '\'' +
                ", ticktime='" + ticktime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubmitId() {
        return submitId;
    }

    public void setSubmitId(Integer submitId) {
        this.submitId = submitId;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public Integer getSubmitStutsa() {
        return submitStutsa;
    }

    public void setSubmitStutsa(Integer submitStutsa) {
        this.submitStutsa = submitStutsa;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getUrlnum() {
        return urlnum;
    }

    public void setUrlnum(String urlnum) {
        this.urlnum = urlnum;
    }

    public String getForcedBootState() {
        return forcedBootState;
    }

    public void setForcedBootState(String forcedBootState) {
        this.forcedBootState = forcedBootState;
    }

    public String getTicktime() {
        return ticktime;
    }

    public void setTicktime(String ticktime) {
        this.ticktime = ticktime;
    }
}
