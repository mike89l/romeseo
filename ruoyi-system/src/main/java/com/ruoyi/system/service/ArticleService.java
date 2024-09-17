package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.Article;

import java.util.List;

public interface ArticleService {


//    文章列表
    public List<Article> selectArticleList(Article article);


//    查询文章
    public List<Article> selectArticle(Article article);

//    新增文章
    public int insertArticle(Article article);


}
