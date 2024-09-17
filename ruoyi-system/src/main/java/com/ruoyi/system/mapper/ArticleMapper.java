package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectArticleList(Article article);

    public int insertArticle(Article article);

}
