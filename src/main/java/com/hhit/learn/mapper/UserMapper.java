package com.hhit.learn.mapper;

import com.hhit.learn.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * The interface User mapper.
 *
 * @author GeekYe
 * @Description:
 * @Date 2018 /04/15
 * @program: learn
 * @description: 学生教师的Mapper
 * @author: GeekYe
 * @create: 2018 -04-08 12:40
 */
@Mapper
@Component
public interface UserMapper {


    /**
     * Save user.
     *
     * @param userSid      the user sid
     * @param userName     the user name
     * @param userPassword the user password
     * @param userCollege  the user college
     * @param userClass    the user class
     */
    void saveUser(@Param(value = "userSid") String userSid, @Param(value ="userName") String userName,
                  @Param(value = "userPassword") String userPassword, @Param(value = "userCollege") String userCollege,
                  @Param(value = "userClass") String userClass);


    /**
     * Gets user.
     *
     * @param userName     the user name
     * @param userPassword the user password
     * @return the user
     */
    UserEntity getUser(@Param(value = "userName") String userName,
                       @Param(value = "userPassword") String userPassword);
}
