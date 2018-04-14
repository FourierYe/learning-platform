package com.hhit.learn.controller;

import com.hhit.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param userSid         the user sid
     * @param userName        the user name
     * @param userPassword    the user password
     * @param userCollege     the user college
     * @param userClass       the user class
     * @param confirmPassword the confirm password
     * @param model           the model
     * @return the string
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String saveUser(@RequestParam(value = "userSid") String userSid, @RequestParam(value = "userName") String userName,
                           @RequestParam(value = "userPassword") String userPassword, @RequestParam(value = "userCollege") String userCollege,
                           @RequestParam(value = "userClass", defaultValue = "教师") String userClass, @RequestParam(value = "confirmPassword") String confirmPassword,
                           Model model){

        if(userPassword.equals(confirmPassword)){


            if(!userSid.equals("") && !userName.equals("") && !userCollege.equals("") && !userPassword.equals("") ){

                userService.saveUser(userSid, userName, userPassword, userCollege, userClass);
                return "templates/register";
            }else{

                model.addAttribute("message","表单数据未正确填写，请重新填写");
                return "templates/register";
            }
        }else {

            model.addAttribute("passwordMessage","两次密码不正确，请重新填写");
            return "templates/register";
        }


    }
}
