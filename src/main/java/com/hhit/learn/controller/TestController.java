package com.hhit.learn.controller;

import com.github.pagehelper.PageHelper;
import com.hhit.learn.entity.ArticleEntity;
import com.hhit.learn.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: learn
 * @description: 测试
 * @author: GeekYe
 * @create: 2018-04-21 22:15
 **/
@RestController
public class TestController {

    @Autowired
    private ArticleMapper articleMapper;

    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @RequestMapping(value = "/test")
    public List<ArticleEntity> test(){

        PageHelper.startPage(1,5);
        return articleMapper.listArticlesByCategory("测试");
    }
}
