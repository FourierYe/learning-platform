package com.hhit.learn.entity;

/**
 * The type Admin entity.
 *
 * @program: learn
 * @description: 管理员的Entity
 * @author: 叶志鹏
 * @create: 2018 -04-04 12:53
 */
public class AdminEntity {

    private Integer pkAdminId;
    private String adminName;
    private String adminPassword;

    /**
     * Sets pk admin id.
     *
     * @param pkAdminId the pk admin id
     */
    public void setPkAdminId(Integer pkAdminId) {
        this.pkAdminId = pkAdminId;
    }

    /**
     * Sets admin name.
     *
     * @param adminName the admin name
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * Sets admin password.
     *
     * @param adminPassword the admin password
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /**
     * Gets admin name.
     *
     * @return the admin name
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * Gets admin password.
     *
     * @return the admin password
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * Gets pk admin id.
     *
     * @return the pk admin id
     */
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
