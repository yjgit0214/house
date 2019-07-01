package com.kgc.house.protal.controller;

import com.kgc.house.entity.Users;
import com.kgc.house.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class UserController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("checkUname")
    @ResponseBody
    public String checkUname(String username){
        //调用业务
        int temp = usersService.checkUname(username);
        return "{\"result\":"+temp+"}";  //返回json
    }

    @RequestMapping("regUser")
    public String regUser(Users users){
        //调用业务，注册
        int temp = usersService.addUser(users);
        if (temp>0){
            return "login";
        }else {
            return "error";
        }
    }
    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpServletRequest request, HttpSession session){
        //调用业务
        Users users = usersService.login(username,password);
        if (users==null){
            model.addAttribute("info","用户名密码错误");
            return "login";
        }else {
            //只要登入，使用session或者cookie保存登入的信息
            session.setAttribute("users",users);
            session.setMaxInactiveInterval(600);//30秒
            return "redirect:getUserHouse";//进入后到管理租房页面（也可以设置到用户中心的管理页面
        }
    }
}
