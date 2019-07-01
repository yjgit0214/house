package com.kgc.house.protal.controller;

import com.kgc.house.entity.District;
import com.kgc.house.service.impl.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("districtController2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    //发送请求获取类型的异步数据
    @RequestMapping("getDistrict")
    @ResponseBody
    public List<District> getType(){
        List<District> districts = districtService.getAllDistrict();
        return districts;
    }
}
