package com.jx.securityjwt.service;

import com.jx.securityjwt.pojo.User;
import com.jx.securityjwt.util.Result;

public interface LoginService {
    Result login(User user);
}
