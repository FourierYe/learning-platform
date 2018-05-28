package com.hhit.learn.entity;

/**
 * The type Article entity.
 *
 * @program: learn
 * @description: 文章的实体类
 * @author: GeekYe
 * @create: 2018 -04-08 13:29
 */
public class ArticleEntity {

    private Integer pkArticleId;
    private UserEntity userEntity;
    private String articleTime;
    private String articleCategory;
    private String articleTitle;
    private String articleContent;
    private String articleMarkdown;

    public String getArticleMarkdown() {
        return articleMarkdown;
    }

    public void setArticleMarkdown(String articleMarkdown) {
        this.articleMarkdown = articleMarkdown;
    }

    /**
     * Gets pk article id.
     *
     * @return the pk article id
     */
    public Integer getPkArticleId() {
        return pkArticleId;
    }

    /**
     * Sets pk article id.
     *
     * @param pkArticleId the pk article id
     */
    public void setPkArticleId(Integer pkArticleId) {
        this.pkArticleId = pkArticleId;
    }

    /**
     * Gets user entity.
     *
     * @return the user entity
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * Sets user entity.
     *
     * @param userEntity the user entity
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Gets article time.
     *
     * @return the article time
     */
    public String getArticleTime() {
        return articleTime;
    }

    /**
     * Sets article time.
     *
     * @param articleTime the article time
     */
    public void setArticleTime(String articleTime) {
        this.articleTime = articleTime;
    }

    /**
     * Gets article category.
     *
     * @return the article category
     */
    public String getArticleCategory() {
        return articleCategory;
    }

    /**
     * Sets article category.
     *
     * @param articleCategory the article category
     */
    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    /**
     * Gets article title.
     *
     * @return the article title
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * Sets article title.
     *
     * @param articleTitle the article title
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * Gets article content.
     *
     * @return the article content
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * Sets article content.
     *
     * @param articleContent the article content
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

}
