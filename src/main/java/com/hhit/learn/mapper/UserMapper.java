package com.hhit.learn.mapper;

import com.hhit.learn.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @program: learn
 * @description: 学生教师的Mapper
 * @author: GeekYe
 * @create: 2018-04-08 12:40
 **/
@Mapper
@Component
public interface UserMapper {

    /**
    * @Description:  注册用户信息
    * @Param: userSid 用户的学号教职工号 userName用户的姓名 userPassword用户密码 userCollege用户学院 userClass用户班级
    * @return: void
    * @Author: GeekYe
    * @Date: 2018/4/8
    */
    void saveUser(@Param(value = "userSid") String userSid, @Param(value ="userName") String userName,
                  @Param(value = "userPassword") String userPassword, @Param(value = "userCollege") String userCollege,
                  @Param(value = "userClass") String userClass);

}
