package com.hhit.learn.service;

import com.hhit.learn.entity.AdminEntity;
import com.hhit.learn.mapper.AdminMapper;
import com.hhit.learn.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Admin service.
 *
 * @program: learn
 * @description: Admin的Service
 * @author: GeekYe
 * @create: 2018 -04-05 16:09
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * Sets admin mapper.
     *
     * @param adminMapper the admin mapper
     */
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * Gets admin.
     *
     * @param adminName     the admin name
     * @param adminPassword the admin password
     * @return the admin
     */
    public AdminEntity getAdmin(String adminName, String adminPassword) {

        AdminEntity adminEntity = adminMapper.getAdmin(MD5Util.generateMd5(adminName), MD5Util.generateMd5(adminPassword));
        return adminEntity;
    }


}
