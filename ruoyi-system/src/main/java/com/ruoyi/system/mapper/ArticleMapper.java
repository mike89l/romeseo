package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectArticleList(Article article);

    List<Article> selectArticle(Article article);

    Article selectArticleById(Long articleId);

    public int insertArticle(Article article);

    public int deleteArticleById(Long articleId);

    public int deleteArticleByIds(Long[] articleIds);


}
