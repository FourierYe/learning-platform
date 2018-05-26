package com.hhit.learn.controller;

import com.github.pagehelper.PageInfo;
import com.hhit.learn.entity.ArticleEntity;
import com.hhit.learn.entity.UserEntity;
import com.hhit.learn.service.ArticleService;
import com.hhit.learn.service.UserService;
import com.hhit.learn.util.RegexUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The type User controller.
 *
 * @program: learn
 * @description: 用户的Controller
 * @author: GeekYe
 * @create: 2018 -04-14 20:25
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    /**
     * Sets user service.
     *
     * @param userService the user service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Save user string.
     *
     * @param userSid            the user sid
     * @param userName           the user name
     * @param userPassword       the user password
     * @param userCollege        the user college
     * @param userClass          the user class
     * @param confirmPassword    the confirm password
     * @param Token              the token
     * @param httpSession        the http session
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String saveUser(@RequestParam(value = "userSid") String userSid, @RequestParam(value = "userName") String userName,
                           @RequestParam(value = "userPassword") String userPassword, @RequestParam(value = "userCollege") String userCollege,
                           @RequestParam(value = "userClass", defaultValue = "教师") String userClass, @RequestParam(value = "confirmPassword") String confirmPassword,
                           @RequestParam(value = "Token") String Token, HttpSession httpSession, RedirectAttributes redirectAttributes){



        String SessionToken = (String) httpSession.getAttribute("Session_Token");
        httpSession.removeAttribute("Session_Token");

        /**
         * 判断是否是邮箱*/
        if(!RegexUtil.isEmail(userName)){

            return "redirect:/register";

        }

        /**
         * 防止表单多次提交*/
        if(SessionToken.equals(Token)) {
            /**
             * 防止两次密码不相同*/
            if (userPassword.equals(confirmPassword)) {
                /**
                 * 防止注册信息为空*/
                if (!userSid.equals("") && !userName.equals("") && !userCollege.equals("") && !userPassword.equals("")) {

                    userService.saveUser(userSid, userName, userPassword, userCollege, userClass);
                    redirectAttributes.addAttribute("message", "请登陆您的账号");

                    return "redirect:/student/login";
                } else {

                    redirectAttributes.addAttribute("message", "表单数据未正确填写，请重新填写");
                    return "redirect:/register";
                }
            } else {

                redirectAttributes.addAttribute("passwordMessage", "两次密码不正确，请重新填写");
                return "redirect:/register";
            }

        }else {

            redirectAttributes.addAttribute("message", "请不要多次提交表单");
            return "redirect:/register";
        }
    }

    /**
     * Do login string.
     *
     * @param userName           the user name
     * @param userPassword       the user password
     * @param httpSession        the http session
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping(value = "/user/doLogin", method = {RequestMethod.POST,RequestMethod.GET})
    public String doLogin(@RequestParam(value = "userName") String userName,
                          @RequestParam(value = "userPassword") String userPassword,
                          HttpSession httpSession, RedirectAttributes redirectAttributes){

        UserEntity userEntity = userService.getUser(userName, userPassword);

        if(userEntity == null){

            redirectAttributes.addAttribute("message","账号密码不正确");
            return "redirect:/student/login";

        }else {

            httpSession.setAttribute("USER_ID",userEntity.getPkUserId());
            return "templates/user_home";
        }

    }

    /**
     * Safety exit string.
     *
     * @param httpSession the http session
     * @return the string
     */
    @RequestMapping(value = "/safetyExit", method = RequestMethod.GET)
    public String safetyExit(HttpSession httpSession){

        httpSession.invalidate();
        return "redirect:/home";
    }

    /**
     * Delete article string.
     *
     * @param pageNum     the page num
     * @param httpSession the http session
     * @param model       the model
     * @return the string
     */
    @RequestMapping(value = "/user/showDeleteArticle", method = RequestMethod.GET)
    public String showDeleteArticle(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    HttpSession httpSession,
                                    Model model){

        Integer userId = (Integer) httpSession.getAttribute("USER_ID");

        PageInfo pageInfo = articleService.listArticlesByUserId(userId, pageNum);
        List<ArticleEntity> articleEntityList = pageInfo.getList();
        pageNum = pageInfo.getPageNum();
        Integer pages = pageInfo.getPages();

        model.addAttribute("pages", pages);
        model.addAttribute("articleEntityList", articleEntityList);
        model.addAttribute("pageNum", pageNum);

        return "templates/delete_article";
    }

    /**
     * Show update article string.
     *
     * @param pageNum     the page num
     * @param httpSession the http session
     * @param model       the model
     * @return the string
     */
    @RequestMapping(value = "/user/showUpdateArticle")
    public String showUpdateArticle(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    HttpSession httpSession,
                                    Model model){

        Integer userId = (Integer) httpSession.getAttribute("USER_ID");

        PageInfo pageInfo = articleService.listArticlesByUserId(userId, pageNum);
        List<ArticleEntity> articleEntityList = pageInfo.getList();
        pageNum = pageInfo.getPageNum();
        Integer pages = pageInfo.getPages();

        model.addAttribute("pages", pages);
        model.addAttribute("articleEntityList", articleEntityList);
        model.addAttribute("pageNum", pageNum);

        return "templates/show_update_article";

    }

    @RequestMapping(value = "/user/showUpdateArticleById")
    public String showUpdateArticleById(@RequestParam(value = "articleId") Integer articleId,
                                        Model model){

        ArticleEntity articleEntity = articleService.getArticleById(articleId);
        String articleTitle = articleEntity.getArticleTitle();
        String articleCategory = articleEntity.getArticleCategory();
        String articleContent = articleEntity.getArticleContent();

        model.addAttribute("articleTitle", articleTitle);
        model.addAttribute("articleCategory", articleCategory);
        model.addAttribute("articleContent", articleContent);
        model.addAttribute("articleId", articleId);
        return "templates/user_home";
    }
}
