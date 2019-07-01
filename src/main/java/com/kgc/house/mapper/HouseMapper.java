package com.kgc.house.mapper;

import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.entity.HouseExample;
import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //管理租房
    List<House> selectHouseByUserId(Integer uid);
    //查询出租房信息(带区域id)
    House getHouseAndDid(String id);

    //查询审核的出租房
    List<House> getHouseByState(Integer state); //state=0表示未审核,state=1审核

    //查询浏览的出租房信息
    List<House> getHouseByBrowser(HouseCondition condition);
}