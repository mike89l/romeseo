package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.domain.entity.PjtConfig;
import com.ruoyi.system.mapper.ArticleMapper;
import com.ruoyi.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public List<Article> selectArticle(Article article) {
        List<Article> articles = articleMapper.selectArticle(article);
        return articles;
    }

    @Override
    public int insertArticle(Article article) {
        article.setCreateTime(PjtConfig.getDate());
        article.setUpdateTime(PjtConfig.getDate());
        article.setStatus(PjtConfig.getOpen());
        int rows = articleMapper.insertArticle(article);
        return rows;
    }
}
