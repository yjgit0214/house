package com.kgc.house.service.impl;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;

import java.util.List;

public interface StreetService {
    //通过区域显示街道
    PageInfo<Street> getStreetByDistrict(Integer page, Integer pageSize, Integer districtId);
    //发布页面根据区域的id显示getStreetByDidstrict对应的街道
    List<Street> getStreetByDistrict(Integer districtId);
}
