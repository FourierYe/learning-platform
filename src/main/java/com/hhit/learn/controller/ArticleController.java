package com.hhit.learn.controller;

import com.hhit.learn.entity.ArticleEntity;
import com.hhit.learn.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * The type Article controller.
 *
 * @program: learn
 * @description: 文章的Controller
 * @author: GeekYe
 * @create: 2018 -04-16 22:50
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Sets article service.
     *
     * @param articleService the article service
     */
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Save article.
     *
     * @param articleTitle    the article title
     * @param articleCategory the article category
     * @param articleContent  the article content
     * @param httpSession     the http session
     * @return the string
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/user/saveArticle", method = RequestMethod.POST)
    public String saveArticle(@RequestParam(value = "articleTitle") String articleTitle,
                            @RequestParam(value = "articleCategory") String articleCategory,
                            @RequestParam(value = "articleContent") String articleContent,
                            HttpSession httpSession){

        System.out.println("controller"+articleContent);

        Integer userId = (Integer) httpSession.getAttribute("USER_ID");

        articleService.saveArticle(userId, articleTitle, articleCategory, articleContent);
        ArticleEntity articleEntity = articleService.getArticleByUserTimeLimitOne(userId);

        httpSession.setAttribute("articleId", articleEntity.getPkArticleId().toString());

        return "templates/article_file";
    }


}
