package com.kgc.house.service.impl;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersCondition;

public interface UsersService {
    //查询区域并分页
    PageInfo<Users> getUsersByPage(UsersCondition condition);
    //检查用户名是否存在
    public int checkUname(String username);
    //添加用户,注册
    public int addUser(Users users);
    //实现登入
    public Users login(String username,String password);


}
