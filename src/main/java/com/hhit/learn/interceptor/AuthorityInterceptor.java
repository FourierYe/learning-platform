package com.hhit.learn.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: 将AuthorityHandlerInterceptor拦截器注册到Spring
 * @Author: GeekYe
 * @Date: 2018/3/6
 */
@Component
public class AuthorityInterceptor extends WebMvcConfigurerAdapter {
    /**
     * @Description: 将AuthorityHandlerInterceptor拦截器注册到Spring,匹配/user/下的所有路径
     * @Param: [registry]
     * @return: void
     * @Author: GeekYe
     * @Date: 2018/3/6
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityHandlerInterceptor())
                //添加需要验证登录用户操作权限的请求
                .addPathPatterns("/user/**").excludePathPatterns("/user/doLogin");
        //排除不需要验证登录用户操作权限的请求

    }
}