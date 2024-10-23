package com.heima.model.user.dtos;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {
    /**
     * 手机号字段，用于存储用户手机号码
     */
    @ApiModelProperty(value = "手机号",required = true)
    private String phone;
    /**
     * 密码字段，用于存储用户账户密码
     */
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
