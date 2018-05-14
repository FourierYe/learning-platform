package com.hhit.learn.controller;

import com.hhit.learn.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "/user/saveArticle", method = RequestMethod.POST)
    public String saveArticle(@RequestParam(value = "articleTitle") String articleTitle,
                            @RequestParam(value = "articleCategory") String articleCategory,
                            @RequestParam(value = "articleContent") String articleContent,
                            HttpSession httpSession){

        Integer userId = (Integer) httpSession.getAttribute("USER_ID");

        articleService.saveArticle(userId, articleTitle, articleCategory, articleContent);

        return "templates/article_file";
    }


}
