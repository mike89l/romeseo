package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectArticleList();

    List<Article> selectArticle(Article article);

    public int insertArticle(Article article);



}
