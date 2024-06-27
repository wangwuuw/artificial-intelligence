package net.cowfish.controller;

import net.cowfish.common.ResponseWrapper;
import net.cowfish.entity.ArticleModel;
import net.cowfish.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("article/insertArticle")
    public ResponseWrapper insertArticle(@RequestBody ArticleModel article) {

        int i = articleService.insertArticle(article);
        if (i == 1) {
            return ResponseWrapper.ok();
        }
        return ResponseWrapper.fail("数据未插入");
    }

    @PostMapping("article/queryArticle")
    public ResponseWrapper queryArticle(@RequestBody(required = false) ArticleModel article) {
        List<ArticleModel> articleModels = articleService.queryArticle(article);
        return ResponseWrapper.ok(articleModels);
    }

    @PostMapping("article/updateArticle")
    public ResponseWrapper updateArticle(@RequestBody ArticleModel article) {
        int i = articleService.updateArticle(article);
        if (i == 1) {
            return ResponseWrapper.ok();
        }
        return ResponseWrapper.fail("更新未成功");
    }

    @GetMapping("article/deleteArticle")
    public ResponseWrapper deleteArticle(@RequestParam(name = "id") Long id) {
        int i = articleService.deleteArticle(id);
        if (i == 1) {
            return ResponseWrapper.ok();
        }
        return ResponseWrapper.fail("删除未成功");
    }
    @GetMapping("article/queryNewArticle")
    public ResponseWrapper queryNewArticle() {
        List<ArticleModel> articleModels = articleService.queryNewArticle();
        return ResponseWrapper.ok(articleModels);
    }
    @GetMapping("article/queryRecommendArticle")
    public ResponseWrapper queryRecommendArticle() {
        List<ArticleModel> articleModels = articleService.queryRecommendArticle();
        return ResponseWrapper.ok(articleModels);
    }
}
