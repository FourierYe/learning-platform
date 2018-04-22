package com.hhit.learn.service;

import com.hhit.learn.entity.ArticleEntity;
import com.hhit.learn.mapper.ArticleMapper;
import com.hhit.learn.util.SubHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The type Article service.
 *
 * @program: learn
 * @description: Article的Service
 * @author: GeekYe
 * @create: 2018 -04-19 12:25
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * Sets article mapper.
     *
     * @param articleMapper the article mapper
     */
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * Save article.
     *
     * @param userId          the user id
     * @param articleTitle    the article title
     * @param articleCategory the article category
     * @param articleContent  the article content
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(Integer userId, String articleTitle, String articleCategory, String articleContent){

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:SS");
        String articleTime = simpleDateFormat.format(date);
        articleMapper.saveArticle(userId, articleTime, articleTitle, articleCategory, articleContent);

    }

    /**
     * List articles on home list.
     *
     * @return the list
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ArticleEntity> listArticlesOnHome(){
        List<ArticleEntity> articleEntityList = articleMapper.listArticlesOnHome();
        for (ArticleEntity a : articleEntityList
             ) {
            String oriHTML = a.getArticleContent();
            String restHTML = SubHTML.subStringHTML(oriHTML, 600 , "...");
            a.setArticleContent(restHTML);
        }
        return articleEntityList;
    }

    /**
     * Get article by id article entity.
     *
     * @param pkArticleId the pk article id
     * @return the article entity
     */
    public ArticleEntity getArticleById(Integer pkArticleId){

        return articleMapper.getArticleById(pkArticleId);
    }
}
