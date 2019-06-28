package com.xulu.demo.mapper;

import com.xulu.demo.entity.User;
import com.xulu.demo.framework.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
/**
 * @author xulu on 2019/3/11.
 */
public interface UserMapper extends MyMapper<User> {

    List<Integer> selectIdsByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("start") Integer start, @Param("limit") Integer limit);

}
