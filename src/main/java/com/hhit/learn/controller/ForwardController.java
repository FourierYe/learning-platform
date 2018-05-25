package com.hhit.learn.controller;

import com.github.pagehelper.PageInfo;
import com.hhit.learn.entity.ArticleEntity;
import com.hhit.learn.service.ArticleService;
import com.hhit.learn.util.TokenUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * The type Forward controller.
 *
 * @program: learn
 * @description: 页面跳转的controller
 * @author: GeekYe
 * @create: 2018 -04-01 13:36
 */
@Controller
public class ForwardController {

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
     * Show index string.
     *
     * @return the string
     */
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String showIndex(){

        return "templates/index";
    }

    /**
     * Show home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(Model model){
        //添加数据到model
        List<ArticleEntity> articleEntityList = articleService.listArticlesOnHome();
        model.addAttribute("articleEntityList", articleEntityList);
        return "templates/home";
    }

    /**
     * Show login string.
     *
     * @param message the message
     * @param model   the model
     * @return the string
     */
    @RequestMapping(value = "/student/login", method = RequestMethod.GET)
    public String showLogin(@RequestParam(value = "message", defaultValue = "") String message,
                            Model model){
        model.addAttribute("message", message);
        return "templates/login";
    }

    /**
     * Show register string.
     *
     * @param message         the message
     * @param passwordMessage the password message
     * @param model           the model
     * @param httpSession     the http session
     * @return the string
     */
    @RequestMapping(value = "/register", method = { RequestMethod.POST, RequestMethod.GET })
    public String showRegister(@RequestParam(value = "message", defaultValue = "") String message,
                               @RequestParam(value = "passwordMessage", defaultValue = "") String passwordMessage,
                               Model model, HttpSession httpSession){

        httpSession.removeAttribute("Session_Token");
        String Token = new TokenUtil().getToken();
        httpSession.setAttribute("Session_Token", Token);
        model.addAttribute("Token", Token);
        model.addAttribute("message", message);
        model.addAttribute("passwordMessage", passwordMessage);
        return "templates/register";
    }

    /**
     * Show admin login string.
     *
     * @return the string
     */
    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    public String showAdminLogin(){

        return "templates/admin_login";
    }

    /**
     * Show write string.
     *
     * @return the string
     */
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String showWrite(){

        return "templates/write";
    }

    /**
     * Show article string.
     *
     * @param pkArticleId the pk article id
     * @param model       the model
     * @return the string
     */
    @RequestMapping(value = "/showArticleById", method = RequestMethod.GET)
    public String showArticle(@RequestParam(value = "articleId") Integer pkArticleId,
                              Model model){
        File file = new File("/Files"+"/"+pkArticleId.toString());
        String [] filesName = file.list();
        model.addAttribute("filesName", filesName);
        model.addAttribute("articleId", pkArticleId.toString());
        ArticleEntity articleEntity = articleService.getArticleById(pkArticleId);
        model.addAttribute("article", articleEntity);
        Integer beforeNum = articleService.countBeforeArticleId(pkArticleId);
        model.addAttribute("beforeNum", beforeNum);

        return "templates/article";
    }

    /**
     * Forward save article string.
     *
     * @return the string
     */
    @RequestMapping(value = "/user/forwardSaveArticle", method = RequestMethod.GET)
    public String forwardSaveArticle(){

        return "templates/user_home";
    }

    /**
     * Forward upload file string.
     *
     * @param model       the model
     * @param httpSession the http session
     * @return the string
     */
    @RequestMapping(value = "/user/forwardUploadFile", method = RequestMethod.GET)
    public String forwardUploadFile(Model model, HttpSession httpSession){

        String articleId = (String) httpSession.getAttribute("articleId");

        File file = new File("/Files"+"/"+articleId);
        String [] filesname = file.list();
        model.addAttribute("filesname", filesname);
        return "templates/upload_file";
    }

    /**
     * Show articles by category string.
     *
     * @param articleCategory the article category
     * @param pageNum         the page num
     * @param model           the model
     * @return the string
     */
    @RequestMapping(value = "/showArticlesByCategory")
    public String showArticlesByCategory(@RequestParam(value = "articleCategory", defaultValue = "数学物理") String articleCategory,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         Model model){


        PageInfo pageInfo = articleService.listArticlesByCategory(articleCategory, pageNum);

        //要返回的articleEntityList
        List<ArticleEntity> articleEntityList = pageInfo.getList();
        //获取页数
        Integer pages = pageInfo.getPages();

        model.addAttribute("articleEntityList", articleEntityList);
        model.addAttribute("pages", pages);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("articleCategory", articleCategory);

        return "templates/articles";
    }

    /**
     * Gets article by direction.
     *
     * @param beforeNum the before num
     * @param direction the direction
     * @param model     the model
     * @return the article by direction
     */
    @RequestMapping(value = "/getArticleByDirection", method = RequestMethod.GET)
    public String getArticleByDirection(@RequestParam(value = "beforeNum") Integer beforeNum,
                                        @RequestParam(value = "direction") String direction,
                                        Model model){

        if(direction.equals("Previous")){
            PageInfo pageInfo = articleService.getArticleByDirectionPrevious(beforeNum);

            List<ArticleEntity> articleEntityList = pageInfo.getList();

            model.addAttribute("articleEntityList", articleEntityList);
            model.addAttribute("beforeNum", beforeNum-1);
        }

        if(direction.equals("Next")){
            PageInfo pageInfo = articleService.getArticleByDirectionNext(beforeNum+1);

            List<ArticleEntity> articleEntityList = pageInfo.getList();

            model.addAttribute("articleEntityList", articleEntityList);
            model.addAttribute("beforeNum", beforeNum+1);
        }

        return "templates/article";
    }

    @RequestMapping(value = "/user/showSaveArticle")
    public String showSaveArticle(){

        return "templates/user_home";
    }
}
