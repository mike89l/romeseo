package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.domain.entity.ProjectConfig;
import com.ruoyi.system.mapper.ArticleMapper;
import com.ruoyi.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Article> selectArticleList(Article article) {
//        return Collections.emptyList();
        List<Article> articles = articleMapper.selectArticleList(article);
        return articles;
    }

    @Override
    public int insertArticle(Article article) {
        Date date = new Date();
        article.setCreateTime(date);
        article.setUpdateTime(date);
        article.setStatus(ProjectConfig.getOpen());
        int rows = articleMapper.insertArticle(article);
        return rows;
    }
}
