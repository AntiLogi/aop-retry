package com.ayyovei.aop.retry.web;

import com.ayyovei.aop.retry.entity.User;
import com.ayyovei.aop.retry.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenxiaobin
 * @description
 * @create 2019/6/23
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable int id){
        return userService.findUserById(id);
    }
    @GetMapping(path = "/anno/{id}")
    public User getUserByIdUserAnnotation(@PathVariable int id){
        return userService.findUserByIdUserAnnotation(id);
    }
}
