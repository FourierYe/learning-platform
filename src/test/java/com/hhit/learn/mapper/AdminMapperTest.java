package com.hhit.learn.mapper;

import com.hhit.learn.entity.AdminEntity;
import com.hhit.learn.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: learn
 * @description: AdminMapper的测试类
 * @author: 叶志鹏
 * @create: 2018-04-04 11:57
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
    *
     * @Description: 保存管理员的测试方法
     * @return: void
     * @Author: 叶志鹏
     * @Date: 2018/4/4
     */
    @Test
    public void saveAdminTest(){

        adminMapper.saveAdmin(MD5Util.generateMd5("939647181@qq.com"),MD5Util.generateMd5("12345678"));

    }

    /**
    *
     * @Description: 通过ID删除管理员记录的测试方法
     * @return: void
     * @Author: 叶志鹏
     * @Date: 2018/4/4
     */
    @Test
    public void deleteAdminTest(){

        adminMapper.deleteAdmin(1);
    }

    /**
    *
     * @Description: 通过ID修改Admin账号密码的测试方法
     * @return: void
     * @Author: 叶志鹏
     * @Date: 2018/4/4
     */
    @Test
    public void updateAdmin(){

        adminMapper.updateAdminPassword("939647181@qq.com","123456");

    }

    /**
    *
     * @Description: 通过账号密码查询管理员的测试方法
     * @return: void
     * @Author: 叶志鹏
     * @Date: 2018/4/4
     */
    @Test
    public void getAdmin(){

        AdminEntity adminEntity=adminMapper.getAdmin(MD5Util.generateMd5("yezhipeng"),MD5Util.generateMd5("12345678"));
        System.out.println(adminEntity.toString());
    }
}
