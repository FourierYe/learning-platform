package com.hhit.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "templates/home";
    }

    /**
     * Show login string.
     *
     * @return the string
     */
    @RequestMapping(value = "/student/login", method = RequestMethod.GET)
    public String showLogin(){

        return "templates/login";
    }
    /**
    *
     * @Description: 注册跳转
     * @Author: 叶志鹏
     * @Date: 2018/4/4
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(){


        return "templates/register";
    }
}
