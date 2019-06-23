package com.ayyovei.aop.retry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenxiaobin
 * @description
 * @create 2019/6/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public int id;

    public String name;

    public int age;
}
