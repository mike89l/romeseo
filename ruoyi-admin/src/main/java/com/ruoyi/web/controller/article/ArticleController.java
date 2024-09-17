package com.ruoyi.web.controller.article;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article/add")
public class ArticleController extends BaseController {


    @Autowired
    private ArticleService articleService;


    @PreAuthorize("@ss.hasPermi('article:add:index')")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult list(@Validated @RequestBody Article article)
    {
        List<Article> articles = articleService.selectArticleList(article);
        if (articles!=null && articles.size()>0) {
            return error("新增失败，该文章已存在");
        }
        return toAjax(articleService.insertArticle(article));
    }





}
