package com.hhit.learn.controller;

import com.hhit.learn.entity.AdminEntity;
import com.hhit.learn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * The type Admin controller.
 *
 * @program: learn
 * @description: Admin的Controller
 * @author: GeekYe
 * @create: 2018 -04-05 16:04
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Sets admin service.
     *
     * @param adminService the admin service
     */
    public void setAdminService(AdminService adminService) {

        this.adminService = adminService;

    }


    /**
     * Do login string.
     *
     * @param adminName     the admin name
     * @param adminPassword the admin password
     * @param httpSession   the http session
     * @param model         the model
     * @return the string
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "adminName") String adminName,
                          @RequestParam(value = "adminPassword") String adminPassword,
                          HttpSession httpSession, Model model) {

        AdminEntity adminEntity1 = adminService.getAdmin(adminName, adminPassword );

        if(adminEntity1 != null){

            httpSession.setAttribute("adminJudge","admin");

            return "templates/admin_home";

        }else {

            model.addAttribute("message", "账号密码错误");

            return "templates/admin_login";
        }

    }

}
