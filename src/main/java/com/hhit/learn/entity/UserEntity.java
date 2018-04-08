package com.hhit.learn.entity;

import java.util.List;

/**
 * @program: learn
 * @description: 学生教师的实体类
 * @author: GeekYe
 * @create: 2018-04-08 12:46
 **/
public class UserEntity {

    private Integer pkUserId;
    private String userSid;
    private String userName;
    private String userPassword;
    private String userCollege;
    private Integer userLimit;
    private List<ArticleEntity> articleEntities;


    public List<ArticleEntity> getArticleEntities() {
        return articleEntities;
    }

    public void setArticleEntities(List<ArticleEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }

    public Integer getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(Integer pkUserId) {
        this.pkUserId = pkUserId;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCollege() {
        return userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }

    public Integer getUserLimit() {
        return userLimit;
    }

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
