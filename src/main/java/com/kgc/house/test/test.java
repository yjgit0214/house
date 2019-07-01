package com.kgc.house.test;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.impl.DistrictService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService service = (DistrictService)ctx.getBean("districtServiceImpl");
        PageInfo<District> pageInfo= service.getDistrictByPage(1,3);
        for(District d:pageInfo.getList()){
            System.out.println(d.getName());
        }
    }
}
