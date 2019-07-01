package com.kgc.house.protal.controller;

import com.kgc.house.entity.Type;
import com.kgc.house.service.impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("typeController2")
@RequestMapping("/page/")
public class TypeController {
    @Autowired
    private TypeService typeService;
    //发送请求获取类型的异步数据
    @RequestMapping("getType")
    @ResponseBody
    public List<Type> getType(){
        List<Type> types = typeService.getAllType();
        return types;
    }
}
