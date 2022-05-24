package com.ying.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public boolean login(@RequestBody UserDTO userDTO){


        return userService.login(userDTO);
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

