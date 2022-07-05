package com.jx.securityjwt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jx.securityjwt.mapper.UserMapper;
import com.jx.securityjwt.pojo.LoginUser;
import com.jx.securityjwt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: jiangxing
 * @createDate: 2022/7/1
 * @version: 1.0
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        // 如果用户为null  抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

        // TODO 查询对应的权限

        // 数据封装为UserDetails对象
        return new LoginUser(user);
    }
}
