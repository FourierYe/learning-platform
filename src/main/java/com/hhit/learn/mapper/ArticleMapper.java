package com.hhit.learn.mapper;


import com.hhit.learn.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface Article mapper.
 *
 * @author GeekYe
 * @Description:
 * @Date 2018 /04/08
 */
@Mapper
@Component
public interface ArticleMapper {

    /**
     * Save article.
     *
     * @param userId          the user id
     * @param articleTime     the article time
     * @param articleTitle    the article title
     * @param articleCategory the article category
     * @param articleContent  the article content
     */
    void saveArticle(@Param(value = "userId") Integer userId,
                     @Param(value = "articleTime") String articleTime,
                     @Param(value = "articleTitle") String articleTitle,
                     @Param(value = "articleCategory") String articleCategory,
                     @Param(value = "articleContent") String articleContent);


    /**
     * List articles on home array list.
     *
     * @return the array list
     */
    ArrayList<ArticleEntity> listArticlesOnHome();

    /**
     * Gets article by id.
     *
     * @param pkArticleId the pk article id
     * @return the article by id
     */
    ArticleEntity getArticleById(@Param(value = "pkArticleId") Integer pkArticleId);

    /**
     * List articles by category list.
     *
     * @param articleCategory the article category
     * @return the list
     */
    List<ArticleEntity> listArticlesByCategory(@Param(value = "articleCategory") String articleCategory);
}
