package com.ying.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ying.springboot.controller.dto.UserDTO;
import com.ying.springboot.entity.User;
import com.ying.springboot.mapper.UserMapper;
import com.ying.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        // 处理异常情况
        try {
            User one = getOne(queryWrapper);
            return one != null;
        } catch (Exception e) {
            return false;
        }
    }
}
