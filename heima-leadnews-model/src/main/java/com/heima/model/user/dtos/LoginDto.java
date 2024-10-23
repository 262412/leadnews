package com.heima.model.user.dtos;
import lombok.Data;

@Data
public class LoginDto {
    /**
     * 手机号字段，用于存储用户手机号码
     */
    private String phone;


    /**
     * 密码字段，用于存储用户账户密码
     */
    private String password;
}
