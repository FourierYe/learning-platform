package com.hhit.learn.controller;

import com.hhit.learn.util.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    public String showLogin(@RequestParam(value = "message", defaultValue = "") String message,
                            Model model){
        model.addAttribute("message", message);
        return "templates/login";
    }

    /**
     * Show register string.
     *
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
}
