package com.ying.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ying.springboot.common.Constants;
import com.ying.springboot.common.Result;
import com.ying.springboot.controller.dto.UserDTO;
import com.ying.springboot.entity.User;
import com.ying.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //用户登陆
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto=userService.login(userDTO);
        return Result.success(dto);
    }
    //用户注册
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(userService.register(userDTO));
    }
    //查询所有数据
    @GetMapping
    public List<User> getAll(){return userService.list();}
    //新增和修改
    @PostMapping
    public boolean save(@RequestBody User user){return userService.saveOrUpdate(user);}
    //修改
    //@PutMapping
//    public Boolean update(@RequestBody User user){return UserService.modify(user); }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
       return userService.removeById(id);
    }
    @GetMapping("/username/{username}")
    public User findOne(@PathVariable String username){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userService.getOne(queryWrapper);
    }



    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }
    //分页查询
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam (defaultValue="") String username ,
                                @RequestParam (defaultValue="") String email,
                                @RequestParam (defaultValue="") String address){
        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if (!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)){
            queryWrapper.like("address",address);
        }
        return userService.page(page,queryWrapper);

    }
    }

