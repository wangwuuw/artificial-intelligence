package net.cowfish.dao;

import net.cowfish.entity.ArticleModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(article_title,article_type,article_content,create_time,update_time)" +
            " values(#{articleTitle},#{articleType},#{articleContent},#{createTime},#{updateTime})")
    int insertArticle(ArticleModel articleModel);
    @Insert("select article_title,article_type,article_content,create_time,update_time from article " +
            " where deleted = 0 " +
            "<if test = 'articleType != null'>" +
            " and article_type = #{articleType} " +
            "</if>")
    int queryArticle(ArticleModel articleModel);
    @Insert("update article set article_title = #{articleTitle},article_type = #{articleType},article_content = #{articleContent},update_time = #{updateTime}" +
            " where id = #{id}")
    int updateArticle(ArticleModel articleModel);
    @Insert("update article set deleted = 1 where id = #{id}")
    int deleteArticle(ArticleModel articleModel);
}
