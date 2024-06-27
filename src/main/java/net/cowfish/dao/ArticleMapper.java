package net.cowfish.dao;

import net.cowfish.entity.ArticleModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(article_title,article_type,article_content,recommend,create_time,update_time)" +
            " values(#{articleTitle},#{articleType},#{articleContent},#{recommend},#{createTime},#{updateTime})")
    int insertArticle(ArticleModel articleModel);
    @Select("<script>" +
            "select id,article_title,article_type,article_content,recommend,deleted,create_time,update_time from article " +
            " where deleted = 0 " +
            "<if test = 'articleType != null'>" +
            " and article_type = #{articleType} " +
            "</if>" +
            "</script>")
    @Results(id = "articleMap",value = {
            @Result(property = "articleTitle", column = "article_title"),
            @Result(property = "articleType", column = "article_type"),
            @Result(property = "articleContent", column = "article_content"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")

    })
    List<ArticleModel> queryArticle(ArticleModel articleModel);
    @Update("update article set article_title = #{articleTitle},article_type = #{articleType},article_content = #{articleContent},recommend = #{recommend},update_time = #{updateTime}" +
            " where id = #{id}")
    int updateArticle(ArticleModel articleModel);
    @Update("update article set deleted = 1 where id = #{id}")
    int deleteArticle(@Param("id") Long id);

    @Select("select * from article where deleted = 0 order by create_time desc limit 10")
    @ResultMap(value="articleMap")
    List<ArticleModel> queryNewArticle();

    @Select("select * from article where deleted = 0 and recommend = 1 order by create_time desc")
    @ResultMap(value="articleMap")
    List<ArticleModel> queryRecommendArticle();
}
