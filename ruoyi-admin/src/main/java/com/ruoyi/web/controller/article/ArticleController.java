package com.ruoyi.web.controller.article;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.Article;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article/manage")
public class ArticleController extends BaseController {


    @Autowired
    private ArticleService articleService;


    @PreAuthorize("@ss.hasPermi('article:manage:add')")
    @Log(title = "文章新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult list(@Validated @RequestBody Article article)
    {
        List<Article> articles = articleService.selectArticle(article);
        if (articles!=null && articles.size()>0) {
            return error("新增失败，该文章已存在");
        }
        return toAjax(articleService.insertArticle(article));
    }


    @PreAuthorize("@ss.hasPermi('article:manage:list')")
    @Log(title = "文章列表", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo getarticleList(Article article){
        startPage();
        List<Article> rs = articleService.selectArticleList(article);
        return getDataTable(rs);
    }




}
