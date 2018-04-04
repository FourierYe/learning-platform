package com.hhit.learn.entity;

/**
 * @program: learn
 * @description: 管理员的Entity
 * @author: 叶志鹏
 * @create: 2018-04-04 12:53
 **/
public class AdminEntity {

    private Integer pkAdminId;
    private String adminName;
    private String adminPassword;

    public void setPkAdminId(Integer pkAdminId) {
        this.pkAdminId = pkAdminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public Integer getPkAdminId() {
        return pkAdminId;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "pkAdminId=" + pkAdminId +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }
}
