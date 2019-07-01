package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersCondition;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUsersByPage(UsersCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());

        UsersExample usersExample=new UsersExample();  //条件搜索时用的（Users和UsersExample区别）
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer(1));//数字1表示管理员
        //增加的条件搜索，
        //姓名
        if(condition.getName()!=null){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
        //电话
        if (condition.getTelephone()!=null){
                criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }

        List<Users> list=usersMapper.selectByExample(usersExample);
        PageInfo<Users> pageInfo=new PageInfo<>(list);

        return pageInfo;
    }

    //判断用户名是否存在
    @Override
    public int checkUname(String username) {
//        select * from users where name=
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //添加条件
        criteria.andNameEqualTo(username);
        criteria.andIsadminEqualTo(0);
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.size()==0?0:1 ;
    }

    @Override
    public int addUser(Users users) {
        //设置为前台注册用户
        users.setIsadmin(0);
        //对用户的密码使用MD5加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    //登入
    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(0);
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.size()==1){
            return users.get(0);
        } else {
            return null;
        }
    }

}
