package com.jx.securityjwt.service.impl;

import com.alibaba.fastjson.JSON;
import com.jx.securityjwt.pojo.LoginUser;
import com.jx.securityjwt.pojo.User;
import com.jx.securityjwt.service.LoginService;
import com.jx.securityjwt.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: jiangxing
 * @createDate: 2022/7/1
 * @version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Result login(User user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        LoginUser auth = (LoginUser) authenticate.getPrincipal();
        int authId = auth.getId();
        // 生成随机token
        String token = UUID.randomUUID().toString();
        // 放入redis
        String json = JSON.toJSONString(auth);
        redisTemplate.opsForValue().set(token,json,30, TimeUnit.MINUTES);
        return Result.ok(token);
    }
}
