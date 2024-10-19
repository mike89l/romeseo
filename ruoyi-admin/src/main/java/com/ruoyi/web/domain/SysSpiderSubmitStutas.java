package com.ruoyi.web.domain;

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
    private int id;

    /** url提交id */
    @Excel(name = "url提交id")
    private int submitId;

    /** url选中的池子 */
    @Excel(name = "url选中的池子")
    private String submitType;

    /** 表单状态 */
    @Excel(name = "表单状态")
    private int submitStutsa;

    /** 备注 */
    @Excel(name = "备注")
    private String coment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public int getSubmitStutsa() {
        return submitStutsa;
    }

    public void setSubmitStutsa(int submitStutsa) {
        this.submitStutsa = submitStutsa;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    @Override
    public String toString() {
        return "SysSpiderSubmitStutas{" +
                "id=" + id +
                ", submitId=" + submitId +
                ", submitType='" + submitType + '\'' +
                ", submitStutsa=" + submitStutsa +
                ", coment='" + coment + '\'' +
                '}';
    }
}
