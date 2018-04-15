package com.hhit.learn.controller;

import com.hhit.learn.service.UserService;
import com.hhit.learn.util.RegexUtil;
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

    @RequestMapping(value = "/user/doLogin", method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "userName") String userName,
                          @RequestParam(value = "userPassword") String userPassword){

        userService.getUser(userName, userPassword);
        return "";
    }



}
