package com.jx.securityjwt.controller;

import com.jx.securityjwt.pojo.User;
import com.jx.securityjwt.service.LoginService;
import com.jx.securityjwt.util.Result;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: jiangxing
 * @createDate: 2022/7/1
 * @version: 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        return  loginService.login(user);

    }
}
