package com.du.springboot.dao;

import com.du.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author djg
 */
@Mapper
public interface UserMapper {
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

    /**
     * 通过用户名查询用户信息
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}