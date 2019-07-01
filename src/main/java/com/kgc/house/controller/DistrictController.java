package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.impl.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class DistrictController {
   @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrictAll(Integer page,Integer rows){ //页面、总行数不可变名称
        PageInfo<District> pageInfo=districtService.getDistrictByPage(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addDistrict")
    @ResponseBody
    public String  addDistrict(District district){ //页面、总行数不可变名称
               //调用业务
        int temp = districtService.addDistrict(district);

        return "{\"result\":"+temp+"}";
    }

    @RequestMapping("upDistrict")
    @ResponseBody
    public String  upDistrict(District district){ //页面、总行数不可变名称
        //调用业务
        int temp = districtService.upDistrict(district);
        System.out.println("temp = " + temp);

        return "{\"result\":"+temp+"}";
    }

    @RequestMapping("delDistrict")
    @ResponseBody
    public String delDistrict(Integer id){ //页面、总行数不可变名称
        //调用业务
        int temp = districtService.deleteDistrict(id);
        return "{\"result\":"+temp+"}";

    }
/**
 * 批量删除
 * */
    @RequestMapping("delMoreDistrict")
    @ResponseBody
    public String delMoreDistrict(String ids){  //从页面传递过来的参数,ids="1,2,3"样式的字符串
        //将字符串转换为数组
        String[] arys= ids.split(",");
        Integer[] id=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        int temp = districtService.deleteMoreDistrict(id);
        return "{\"result\":"+temp+"}";

    }
}
