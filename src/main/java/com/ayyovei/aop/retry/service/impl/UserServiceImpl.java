package com.ayyovei.aop.retry.service.impl;


import com.ayyovei.aop.retry.annotation.Retry;
import com.ayyovei.aop.retry.constant.RetryConstant;
import com.ayyovei.aop.retry.entity.User;
import com.ayyovei.aop.retry.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenxiaobin
 * @description
 * @create 2019/6/23
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserService rpcUserService;
    @Override
    public User findUserById(int id) {
        int alreadyTry = 0;
        while (true) {
            try {
              return  rpcUserService.findUserById(id);
            } catch (Exception e) {
                if(++alreadyTry>RetryConstant.MAX_TIMES){
                    logger.error("远程查询失败重试超出最大次数");
                    throw new RuntimeException(e);
                }else {
                    logger.info("远程查询失败,重试{}",alreadyTry);
                }
            }
        }
    }


    @Override
    public User findUserByIdNormal(int id) {
        logger.info("findUserByIdNormal,id={}",id);
        return new User(id, "ayyovei", 10);
    }

    @Override
    @Retry
    public User findUserByIdUserAnnotation(int id) {

        return rpcUserService.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
