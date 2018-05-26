package com.hhit.learn.config;

import com.hhit.learn.filter.XssAndSqlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Web config.
 * @author GeekYe
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * Get demo filter filter registration bean.
     *
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean getDemoFilter(){
        XssAndSqlFilter xssAndSqlFilter = new XssAndSqlFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(xssAndSqlFilter);
        List<String> urlPatterns = new ArrayList();
        //拦截路径，可以添加多个
        urlPatterns.add("/listArticleByContentObscure");
        urlPatterns.add("/user/doLogin");
        urlPatterns.add("/doRegister");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}