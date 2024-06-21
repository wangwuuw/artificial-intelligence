package net.cowfish.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleModel {

    private Long id;
    private String articleTitle;
    private String articleType;
    private String articleContent;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;

}
