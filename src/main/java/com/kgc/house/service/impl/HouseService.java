package com.kgc.house.service.impl;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;

public interface HouseService {
    //发布页面（添加出租房）
    public int addHouse(House house);
    //查询用户的出租房
    public PageInfo<House> getUserHouseByPage(Integer page,Integer rows,Integer uid);
    //查询单条（修改用）
    public House getHouse(String id);
    //修改
    public int updateHouse(House house);
    //删除出租房
    public int delHouse(String id);
    //查询审核的出租房
    PageInfo<House> getHouseByState(Integer page,Integer rows,Integer ispass);
    //审核出租房

    //查询浏览的出租房
    public PageInfo<House> getHouseByBrowser(HouseCondition condition);
}
