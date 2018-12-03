package com.du.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.du.springboot.dao.UserMapper;
import com.du.springboot.entity.User;
import com.du.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author djg
 * @date 2018/12/2 1:50
 * @des
 */
@Component
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<User> getUserByPage(Map<String, Object> params) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("list",userMapper.getUserByPage(params));
        return userMapper.getUserByPage(params);
    }

    @Override
    public int getUserByTotal() {
        return userMapper.getUserByTotal();
    }
}
