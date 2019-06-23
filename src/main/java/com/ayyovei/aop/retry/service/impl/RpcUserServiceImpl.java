package com.ayyovei.aop.retry.service.impl;

import com.ayyovei.aop.retry.entity.User;
import com.ayyovei.aop.retry.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxiaobin
 * @description
 * @create 2019/6/23
 */
@Service("rpcUserService")
public class RpcUserServiceImpl implements UserService {
    @Override
    public User findUserById(int id) {
        throw new RuntimeException("rpc调用异常");
        //return new User(id,"ayyovei",10);
    }

    @Override
    public User findUserByIdNormal(int id) {
        return null;
    }

    @Override
    public User findUserByIdUserAnnotation(int id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
