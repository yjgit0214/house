package com.kgc.house.service.impl;

import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    //发布页面查询所有类型
    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
