package com.du.springboot.service;

import com.du.springboot.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author djg
 * @date 2018/12/2 1:44
 * @des
 */
public interface UserService {
    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    List<User> getUserByPage(Map<String,Object> params);

    /**
     * 分页查询总数
     * @return
     */
    int getUserByTotal();
}
