package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "文章序号", type = Excel.Type.EXPORT, cellType = Excel.ColumnType.NUMERIC, prompt = "文章编号")
    private Long articleId;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String artTitle;

    /** 文章描述 */
    @Excel(name = "文章描述")
    private String artDescription;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String artContent;

    /** 文章状态 */
    @Excel(name = "文章内容")
    private int status;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtDescription() {
        return artDescription;
    }

    public void setArtDescription(String artDescription) {
        this.artDescription = artDescription;
    }

    public String getArtContent() {
        return artContent;
    }

    public void setArtContent(String artContent) {
        this.artContent = artContent;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("articleId", articleId)
                .append("artTitle", artTitle)
                .append("artDescription", artDescription)
                .append("artContent", artContent)
                .append("status", status)
                .toString();
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
