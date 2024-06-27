package net.cowfish.service;

import net.cowfish.dao.ArticleMapper;
import net.cowfish.entity.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    public int insertArticle(ArticleModel article){
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        return articleMapper.insertArticle(article);
    }
    public List<ArticleModel> queryArticle(ArticleModel article){
       return articleMapper.queryArticle(article);
    }
    public int updateArticle(ArticleModel article){
        article.setUpdateTime(new Date());
        return articleMapper.updateArticle(article);
    }
    public int deleteArticle(Long id){
        return articleMapper.deleteArticle(id);
    }

    public List<ArticleModel> queryNewArticle() {
        return articleMapper.queryNewArticle();
    }
    public List<ArticleModel> queryRecommendArticle() {
        return articleMapper.queryRecommendArticle();
    }
}
