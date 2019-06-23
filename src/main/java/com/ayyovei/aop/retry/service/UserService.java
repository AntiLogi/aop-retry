package com.ayyovei.aop.retry.service;

import com.ayyovei.aop.retry.entity.User;

import java.util.List;

/**
 * @author chenxiaobin
 * @description
 * @create 2019/6/23
 */
public interface UserService {

    User findUserById(int id);

    User findUserByIdNormal(int id);

    User findUserByIdUserAnnotation(int id);

    List<User> getAllUsers();
}
