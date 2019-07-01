package com.kgc.house.service.impl;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;

import java.util.List;

public interface DistrictService {
    //查询区域并分页
    PageInfo<District> getDistrictByPage(Integer page,Integer pageSize);
    //添加
    public int addDistrict(District district);
   //修改
    int upDistrict(District district);
    //删除
    int deleteDistrict(Integer id);
    //批量删除区域
    int deleteMoreDistrict(Integer[] ids);

    //发布页面查询所有区域
    List<District> getAllDistrict();
}
