package com.hhit.learn.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

        System.out.println("service"+articleContent);
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

    /**
     * Get article by user time limit one article entity.
     *
     * @param userId the user id
     * @return the article entity
     */
    public ArticleEntity getArticleByUserTimeLimitOne(Integer userId){

        return articleMapper.getArticleByUserTimeLimitOne(userId);
    }

    /**
     * List articles by category list.
     *
     * @param articleCategory the article category
     * @param pageNum         the page num
     * @return the list
     */
    public PageInfo listArticlesByCategory(String articleCategory, Integer pageNum){

        PageHelper.startPage(pageNum,5);
        List<ArticleEntity> articleEntityList = articleMapper.listArticlesByCategory(articleCategory);
        for (ArticleEntity article: articleEntityList
             ) {
            article.setArticleContent(SubHTML.subStringHTML(article.getArticleContent(),600 , "..."));
        }

        PageInfo pageInfo = new PageInfo(articleEntityList);

        return pageInfo ;

    }

    /**
     * Get article by direction page info.
     *
     * @param pageNum the page num
     * @return the page info
     */
    public PageInfo getArticleByDirectionPrevious(Integer pageNum){

        PageHelper.startPage(pageNum, 1);
        List<ArticleEntity> articleEntityList = articleMapper.listArticleByArticleIdSequence();
        PageInfo pageInfo = new PageInfo(articleEntityList);

        return pageInfo;
    }

    /**
     * Get article by direction next page info.
     *
     * @param pageNum the page num
     * @return the page info
     */
    public PageInfo getArticleByDirectionNext(Integer pageNum){

        PageHelper.startPage(pageNum, 1);
        List<ArticleEntity> articleEntityList = articleMapper.listArticleByArticleIdSequence();
        PageInfo pageInfo = new PageInfo(articleEntityList);

        return pageInfo;
    }

    /**
     * Count before article id integer.
     *
     * @param articleId the article id
     * @return the integer
     */
    public Integer countBeforeArticleId(Integer articleId){

        return articleMapper.countBeforeArticleId(articleId);
    }

    /**
     * List articles by user id page info.
     *
     * @param userId  the user id
     * @param pageNum the page num
     * @return the page info
     */
    public PageInfo listArticlesByUserId(Integer userId, Integer pageNum){

        PageHelper.startPage(pageNum, 10);
        List<ArticleEntity> articleEntityList = articleMapper.listArticlesByUserId(userId);
        PageInfo pageInfo = new PageInfo(articleEntityList);

        return pageInfo;
    }

    /**
     * Delete article.
     *
     * @param articleId the article id
     */
    public void deleteArticle(Integer articleId){

        articleMapper.deleteArticle(articleId);

    }

    /**
     * Update article.
     *
     * @param articleId       the article id
     * @param articleTitle    the article title
     * @param articleCategory the article category
     * @param articleContent  the article content
     */
    public void updateArticle(Integer articleId, String articleTitle,
                              String articleCategory, String articleContent){

        articleMapper.updateArticle(articleId, articleTitle, articleCategory, articleContent);
    }

    /**
     * List article by content obscure list.
     *
     * @param articleContent the article content
     * @return the list
     */
    public List<ArticleEntity> listArticleByContentObscure(String articleContent){

        return articleMapper.listArticleByContentObscure(articleContent);
    }
}
