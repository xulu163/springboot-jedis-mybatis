package com.xulu.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.xulu.demo.entity.User;
import com.xulu.demo.framework.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 从redis获取
 * @author xulu
 */
@Service
public class UserCacheService {

    @Autowired
    RedisUtil redisUtil;
    private final String user_key_prix = "user_key";

    public User getById(Integer id){
        User user = null;
        String userString = redisUtil.get(this.user_key_prix + id, 0);
        if(!StringUtils.isEmpty(userString)){
            user = JSONObject.parseObject(userString, User.class);
        }
        return user;
    }

    public void setById(Integer id, User user){
        String userString = "";
        if(null != user){
            userString = JSONObject.toJSONString(user);
        }
        redisUtil.set(this.user_key_prix + id, userString, 0);
    }

    public void deleteById(Integer id){
        redisUtil.del(0,this.user_key_prix + id);
    }
}
