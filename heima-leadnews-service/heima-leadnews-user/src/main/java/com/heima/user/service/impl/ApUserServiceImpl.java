package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    /**
     * 用户登录方法
     *
     * @param dto 登录信息，包含用户手机号和密码
     * @return 登录结果，包括用户信息和token
     */
    @Override
    public ResponseResult login(LoginDto dto) {
        // 检查用户是否输入了手机号和密码
        if (StringUtils.isEmpty(dto.getPhone()) && StringUtils.isEmpty(dto.getPassword())){
            // 根据手机号查询数据库中的用户信息
            ApUser dbUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, dto.getPhone()));
            // 如果用户不存在，返回错误信息
            if(dbUser == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "User not found");
            }
            // 获取用户密码盐值
            String salt = dbUser.getSalt();
            // 获取用户输入的密码
            String password = dto.getPassword();
            // 将用户输入的密码和盐值一起进行MD5加密
            String pswd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            // 比较加密后的密码和数据库中的密码是否一致
            if (!pswd.equals(dbUser.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR, "Password error");
            }
            // 生成用户登录token
            String token = AppJwtUtil.getToken(dbUser.getId().longValue());
            // 创建返回的用户信息map
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            // 清除用户信息中的盐值和密码
            dbUser.setSalt("");
            dbUser.setPassword("");
            map.put("user", dbUser);
            return ResponseResult.okResult(map);
        }else{
            // 如果用户未输入手机号或密码，返回默认登录结果
            Map<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
