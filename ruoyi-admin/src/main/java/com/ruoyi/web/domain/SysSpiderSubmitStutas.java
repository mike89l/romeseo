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
    private Long id;

    /** url提交id */
    @Excel(name = "url提交id")
    private Long submitId;

    /** url选中的池子 */
    @Excel(name = "url选中的池子")
    private String submitType;

    /** 表单状态 */
    @Excel(name = "表单状态")
    private Long submitStutsa;

    /** 备注 */
    @Excel(name = "备注")
    private String coment;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSubmitId(Long submitId) 
    {
        this.submitId = submitId;
    }

    public Long getSubmitId() 
    {
        return submitId;
    }
    public void setSubmitType(String submitType) 
    {
        this.submitType = submitType;
    }

    public String getSubmitType() 
    {
        return submitType;
    }
    public void setSubmitStutsa(Long submitStutsa) 
    {
        this.submitStutsa = submitStutsa;
    }

    public Long getSubmitStutsa() 
    {
        return submitStutsa;
    }
    public void setComent(String coment) 
    {
        this.coment = coment;
    }

    public String getComent() 
    {
        return coment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("submitId", getSubmitId())
            .append("submitType", getSubmitType())
            .append("submitStutsa", getSubmitStutsa())
            .append("coment", getComent())
            .toString();
    }
}
