package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectArticleList(Article article);

    List<Article> selectArticleByTitle(Article article);

    Article selectArticleById(Long articleId);

    public int insertArticle(Article article);

    public int deleteArticleById(Long articleId);

    public int deleteArticleByIds(Long[] articleIds);

    /**
     * 修改文章信息
     *
     * @param article 用户信息
     * @return 结果
     */
    public int updateArticle(Article article);


}
