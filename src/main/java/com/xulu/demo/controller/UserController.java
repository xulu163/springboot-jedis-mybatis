package com.xulu.demo.controller;

import com.xulu.demo.entity.User;
import com.xulu.demo.framework.response.QueryResult;
import com.xulu.demo.service.UserCacheService;
import com.xulu.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xulu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取user信息", notes = "根据id获取user信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户id", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public User getById(@RequestParam Integer id){
        //从缓存中获取
        User user = userCacheService.getById(id);
        if(null == user){
            //缓存为空从db中获取
            user = userService.selectById(id);
            //db结果存入缓存(db中为空也要存入缓存)
            userCacheService.setById(id, user);
        }
        return user;
    }

    /**
     * 新增
     * @param user
     */
    @ApiOperation(value = "提交客户信息", notes = "提交客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user", required = true, paramType = "body", dataType = "User")
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody User user){
        userService.save(user);
    }

    /**
     * 修改
     * @param user
     */
    @ApiOperation(value = "修改客户信息", notes = "修改客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user", required = true, paramType = "body", dataType = "User")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody User user){
        //删除缓存
        userCacheService.deleteById(user.getId());
        //修改db
        userService.update(user);
    }

    /**
     * 分页获取
     * @return
     */
    @ApiOperation(value = "分页获取user信息", notes = "分页获取获取user信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "size", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "page", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    public QueryResult<User> getByPage(@RequestParam Integer size, @RequestParam Integer page){

        //获取满足条件的数据id集
        List<Integer> ids = userService.selectIdsByPageAndSize(page, size);
        //获取总条数
        Integer total = userService.selectTotalByPageAndSize(page, size);
        //遍历id集获取数据
        List<User> list = new ArrayList<User>(10);
        for(Integer id : ids){
            //从缓存获取
            User user = userCacheService.getById(id);
            if(null == user){
                //缓存为空从db中获取
                user = userService.selectById(id);
                //db结果存入缓存
                userCacheService.setById(id, user);
            }
            list.add(user);
        }
        QueryResult<User> queryResult = new QueryResult<User>();
        queryResult.setRows(list);
        queryResult.setTotal(total);
        return queryResult;
    }
}
