package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.domain.entity.PjtConfig;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.ArticleMapper;
import com.ruoyi.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Article> selectArticleList(Article article) {
        return articleMapper.selectArticleList(article);
    }

    @Override
    public Article selectArticleById(Long articleId) {
        Article s = articleMapper.selectArticleById(articleId);
        return s;
    }

    @Override
    public List<Article> selectArticleByTitle(Article article) {
        List<Article> articles = articleMapper.selectArticleByTitle(article);
        return articles;
    }

    @Override
    @Transactional
    public int insertArticle(Article article) {
        article.setCreateTime(PjtConfig.getDate());
        article.setUpdateTime(PjtConfig.getDate());
        if (article.getStatus()==null||article.getStatus().isEmpty()) {
            article.setStatus(PjtConfig.getZero());
        }
        article.setDelFlag(PjtConfig.getZero());
        int rows = articleMapper.insertArticle(article);
        return rows;
    }

    /**
     * 通过文章ID删除文章
     *
     * @param articleId 文章ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteArticleById(Long articleId) {
        return articleMapper.deleteArticleById(articleId);
    }


    /**
     * 批量删除文章信息
     *
     * @param articleIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteArticleByIds(Long[] articleIds) {
        int s = articleMapper.deleteArticleByIds(articleIds);
        return s;
    }

    @Override
    @Transactional
    public int updateArticle(Article article) {
        article.setUpdateTime(PjtConfig.getDate());
        int s = articleMapper.updateArticle(article);
        return s;
    }

    @Override
    public int updateArticleStatus(Article article) {
        return articleMapper.updateArticle(article);
    }
}
