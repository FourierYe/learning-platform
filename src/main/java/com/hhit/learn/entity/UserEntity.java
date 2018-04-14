package com.hhit.learn.entity;

import java.util.List;

/**
 * The type User entity.
 *
 * @program: learn
 * @description: 学生教师的实体类
 * @author: GeekYe
 * @create: 2018 -04-08 12:46
 */
public class UserEntity {

    private Integer pkUserId;
    private String userSid;
    private String userName;
    private String userPassword;
    private String userCollege;
    private Integer userLimit;
    private List<ArticleEntity> articleEntities;


    /**
     * Gets article entities.
     *
     * @return the article entities
     */
    public List<ArticleEntity> getArticleEntities() {
        return articleEntities;
    }

    /**
     * Sets article entities.
     *
     * @param articleEntities the article entities
     */
    public void setArticleEntities(List<ArticleEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }

    /**
     * Gets pk user id.
     *
     * @return the pk user id
     */
    public Integer getPkUserId() {
        return pkUserId;
    }

    /**
     * Sets pk user id.
     *
     * @param pkUserId the pk user id
     */
    public void setPkUserId(Integer pkUserId) {
        this.pkUserId = pkUserId;
    }

    /**
     * Gets user sid.
     *
     * @return the user sid
     */
    public String getUserSid() {
        return userSid;
    }

    /**
     * Sets user sid.
     *
     * @param userSid the user sid
     */
    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets user password.
     *
     * @param userPassword the user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets user college.
     *
     * @return the user college
     */
    public String getUserCollege() {
        return userCollege;
    }

    /**
     * Sets user college.
     *
     * @param userCollege the user college
     */
    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }

    /**
     * Gets user limit.
     *
     * @return the user limit
     */
    public Integer getUserLimit() {
        return userLimit;
    }

    /**
     * Sets user limit.
     *
     * @param userLimit the user limit
     */
    public void setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "pkUserId=" + pkUserId +
                ", userSid='" + userSid + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCollege='" + userCollege + '\'' +
                ", userLimit=" + userLimit +
                ", articleEntities=" + articleEntities +
                '}';
    }
}
