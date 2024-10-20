package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Enterprise extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 企业ID */
    @Excel(name = "企业ID", type = Excel.Type.EXPORT, cellType = Excel.ColumnType.NUMERIC, prompt = "企业ID")
    private Long enterpriseId;

    /** 企业名词 */
    @Excel(name = "企业名称")
    private String enterpriseName;

    /** 企业网址 */
    @Excel(name = "企业网址")
    private String enterpriseUrl;

    /** 企业类型 */
    @Excel(name = "企业类型", readConverterExp = "4=普通企业,5=自营站,6=代理商")
    private String enterpriseType;

    /** 关键词 */
    @Excel(name = "关键词")
    private String keyword;

    /** 创建者ID */
    @Excel(name = "创建者编号", type = Excel.Type.IMPORT)
    private Long createId;

    /** 创建者账号 */
    @Excel(name = "创建者")
    private String createName;

    /** 修改者账号 */
    @Excel(name = "创建者")
    private String updateName;

    /** 企业状态 */
    @Excel(name = "企业状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    @Excel(name = "删除状态", readConverterExp = "0=存在,1=删除")
    private String delFlag;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("enterpriseId", enterpriseId)
                .append("enterpriseName", enterpriseName)
                .append("enterpriseUrl", enterpriseUrl)
                .append("enterpriseType", enterpriseType)
                .append("keyword", keyword)
                .append("createId", createId)
                .append("createName", createName)
                .append("updateName", updateName)
                .append("status", status)
                .append("delFlag", delFlag)
                .toString();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseUrl() {
        return enterpriseUrl;
    }

    public void setEnterpriseUrl(String enterpriseUrl) {
        this.enterpriseUrl = enterpriseUrl;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
