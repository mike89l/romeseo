package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

public interface ArticleService {


    //    文章列表
    public List<Article> selectArticleList(Article article);

    //    查询文章 通过文章ID
    public Article selectArticleById(Long articleId);

    //    查询文章 通过文章标题
    public List<Article> selectArticleByTitle(Article article);

    //    新增文章
    public int insertArticle(Article article);


    /**
     * 通过用户ID删除文章
     *
     * @param articleId 用户ID
     * @return 结果
     */
    public int deleteArticleById(Long articleId);

    /**
     * 通过用户ID批量删除文章
     *
     * @param articleIds 用户ID
     * @return 结果
     */
    public int deleteArticleByIds(Long[] articleIds);


    /**
     * 修改文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    public int updateArticle(Article article);

    public int updateArticleStatus(Article article);


}
