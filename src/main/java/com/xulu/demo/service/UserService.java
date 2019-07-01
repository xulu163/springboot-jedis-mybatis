package com.xulu.demo.service;

import com.xulu.demo.entity.User;
import com.xulu.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
/**
 * @author xulu on 2019/3/11.
 */
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void save(User user){
        user.setCreateDate(new Date());
        userMapper.insert(user);
    }

    public User selectById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public void update(User user){
        user.setUpdateDate(new Date());
        userMapper.updateByPrimaryKey(user);
    }

    public List<Integer> selectIdsByPageAndSize(Integer page, Integer size){
        Integer start = (page - 1) * size;
        return userMapper.selectIdsByPage(start, size);
    }

    public Integer selectTotalByPageAndSize(Integer page, Integer size){
        Integer start = (page - 1) * size;
        return userMapper.selectCountByPage(start, size);
    }
}
