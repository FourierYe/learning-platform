package com.hhit.learn.entity;

/**
 * @program: learn
 * @description: 文章的实体类
 * @author: GeekYe
 * @create: 2018-04-08 13:29
 **/
public class ArticleEntity {

    private Integer pkArticleId;
    private UserEntity userEntity;
    private String articleTime;
    private String articleCategory;
    private String articleTitle;
    private String articleContent;

    public Integer getPkArticleId() {
        return pkArticleId;
    }

    public void setPkArticleId(Integer pkArticleId) {
        this.pkArticleId = pkArticleId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(String articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "pkArticleId=" + pkArticleId +
                ", userEntity=" + userEntity +
                ", articleTime='" + articleTime + '\'' +
                ", articleCategory='" + articleCategory + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                '}';
    }
}
