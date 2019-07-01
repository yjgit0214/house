package com.kgc.house.protal.controller;

import com.kgc.house.entity.Street;
import com.kgc.house.service.impl.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class StreetsController { //发布页面显示对应的街道
    @Autowired
    private StreetService streetService;
    @RequestMapping("getStreetByDid2")
    @ResponseBody
    public List<Street> getStreetByDid2(Integer did){
        List<Street> streets = streetService.getStreetByDistrict(did);
        return streets;
    }

}
