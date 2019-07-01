package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersCondition;
import com.kgc.house.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
   @Autowired
    private UsersService usersService;

    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String,Object> getDistrictAll(UsersCondition condition){ //页面、总行数不可变名称
        PageInfo<Users> pageInfo=usersService.getUsersByPage(condition);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

}
