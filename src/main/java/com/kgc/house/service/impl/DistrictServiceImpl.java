package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<District> getDistrictByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize); //启动分页
        DistrictExample example=new DistrictExample();
//        DistrictExample.Criteria criteria = districtExample.createCriteria();
//        criteria.andNameLike("%城%"); //模糊查询（条件查询）
        List<District> list = districtMapper.selectByExample(example);
        PageInfo<District> pageInfo=new PageInfo(list);

        return pageInfo;
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public int upDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }


    //删除区域
    @Transactional  //事务
    public int deleteDistrict(Integer id) {
        try {
            //删除街道（街道属于区域）   通过区域删除街道
            streetMapper.deleteStreetByDid(id);
            //删除区域
            districtMapper.deleteByPrimaryKey(id);
            return 1;
        } catch (Exception e){
            return 0;
        }

    }

    @Override
    public int deleteMoreDistrict(Integer[] ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

}

