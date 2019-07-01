package com.kgc.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.*;
import com.kgc.house.service.impl.DistrictService;
import com.kgc.house.service.impl.HouseService;
import com.kgc.house.service.impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("goFabu")
    public String goFabu(Model model) throws Exception{
        //查询所有类型
        List<Type> types = typeService.getAllType();
        //查询所有区域
        List<District> districts = districtService.getAllDistrict();
        //使用model将数据传递到页面
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        return "fabu";//跳转页面
    }

    @RequestMapping("addHouse")
    public String addHouse(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile,
                           HttpSession session) throws Exception{
        //将文件保存在服务器中  准备放入D:\\images
        String fname = pfile.getOriginalFilename();
        String expName=fname.substring(fname.lastIndexOf("."));
        String saveName=System.currentTimeMillis()+expName;//保存文件名

        File file=new File("D:\\images\\"+saveName);
        pfile.transferTo(file);  //保存
        //设置编号
        house.setId(System.currentTimeMillis()+"");
        //设置用户编号
        Users user=(Users)session.getAttribute("users");
        house.setUserId(user.getId());
        //审核状态设置0，如果标准有默认值，可不设置
        house.setIspass(0);
        //设置是否删除
        house.setIsdel(0);
        //设置图片路径
        house.setPath(saveName);

        //调用业务

        if (houseService.addHouse(house)>0){
            return "redirect:goFabu";
        }else {
            //file.delete(); //如果发布失败已上传的图片删除
            return "redirect:goFabu";
        }
    }

    //查询出租房 用户id在session中
    @RequestMapping("getUserHouse")
    //page表示页面传的页码
    public String getUserHouse(Integer page,HttpSession session,Model model) throws Exception{
        //调用业务
        Users user=(Users)session.getAttribute("users");
        PageInfo<House> pageInfo = houseService.getUserHouseByPage(page==null?1:page, 2, user.getId());
        //将分页信息存入到作用域
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    //修改显示出租房信息
    @RequestMapping("getHouse")
    public String getHouse(String id,Model model) throws Exception{
        //查询所有类型
        List<Type> types = typeService.getAllType();
        //查询所有区域
        List<District> districts = districtService.getAllDistrict();
        //查询出租房信息  调用查询单个出租房的业务
        House house = houseService.getHouse(id);
        //将数据设置到域中
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        model.addAttribute("house",house);
        return "upfabu";
    }
    //修改出租房
    @RequestMapping("upHouse")
    public String upHouse(String oldPic,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws  Exception{
        //1.修改时判断用户有没有修改图片
        File file = null;
//        if (oldPic=="") {   //如果原来发布管理中没有图片
//
//                String fname = pfile.getOriginalFilename();
//                String expName=fname.substring(fname.lastIndexOf("."));
//                String saveName=System.currentTimeMillis()+expName;//保存文件名
//
//                file=new File("D:\\images\\"+saveName);
//                pfile.transferTo(file);  //保存
//                house.setPath(saveName);
//
//            } else
                if (pfile.getOriginalFilename().equals("")) {
                //System.out.println("不修改图片");
                //不需要实现文件上传，同时house实体的path属性无需设置属性
            } else  {
                //System.out.println("修改图片");
                //上传新的图片，删除旧的图片，设置path为上传新的图片名称
                file = new File("D:\\images\\" + oldPic);
                pfile.transferTo(file);  //保存
                //设置图片名称
                house.setPath(oldPic);
            }

        //保存数据库的记录  house已经接收部分表单数据
        //设置编号  从表单获取
        //设置审核状态 0  如果表中有默认值 可不设
        //house.setIspass(0);
        //设置是否删除  0
        //house.setIsdel(0);
        //设置图片路径
        // house.setPath(saveName);
        if(houseService.updateHouse(house)<=0){
            //成功上传的图片删除
            if(file!=null) file.delete();
        }

        return "redirect:getUserHouse";  //跳转到查询用户出租房
    }
    @RequestMapping("delHouse")
    @ResponseBody    //异步返回需json注解
    public String delHouse(String id) throws Exception{
        int temp = houseService.delHouse(id);
        return "{\"result\":"+temp+"}";

        //return "redirect:getUserHouse";
    }
    //查询所有浏览的出租房信息
    @RequestMapping("goList")
    public String goList(HouseCondition condition, Model model){  //传页码
        //调用业务获取出租房
        PageInfo<House> pageInfo = houseService.getHouseByBrowser(condition);
        //将pageInfo分页信息设置到作用域中
        model.addAttribute("pageInfo",pageInfo);
        if (condition.getTitle()!=null){
            condition.setTitle(condition.getTitle().replace("%",""));
        }
        model.addAttribute("condition",condition); //回显查询的条件信息
        return "list";
    }
}