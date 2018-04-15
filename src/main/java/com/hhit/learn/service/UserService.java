package com.hhit.learn.service;

import com.hhit.learn.entity.UserEntity;
import com.hhit.learn.mapper.UserMapper;
import com.hhit.learn.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 *
 * @program: learn
 * @description: User的Service
 * @author: GeekYe
 * @create: 2018 -04-14 20:25
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Sets user mapper.
     *
     * @param userMapper the user mapper
     */
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    /**
     * Save user.
     *
     * @param userSid      the user sid
     * @param userName     the user name
     * @param userPassword the user password
     * @param userCollege  the user college
     * @param userClass    the user class
     */
    public void saveUser(String userSid, String userName, String userPassword, String userCollege, String userClass){

        userMapper.saveUser(userSid, userName, MD5Util.generateMd5(userPassword), userCollege, userClass);

    }

    public UserEntity getUser(String userName, String userPassword) {

        return userMapper.getUser(userName, MD5Util.generateMd5(userPassword) );
    }
}
