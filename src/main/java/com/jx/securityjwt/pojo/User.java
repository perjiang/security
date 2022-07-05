package com.jx.securityjwt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @description:
 * @author: jiangxing
 * @createDate: 2022/7/1
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("user")
public class User {
    @TableId
    private int id;
    private String username;
    private String password;

}
