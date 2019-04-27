package com.gyy.vaccine.dao;

import com.gyy.vaccine.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

@Mapper
public interface UserMapper {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 如果有，返回
     */
    User findUserByUsername(String username);

    /**
     * 通过用户id查找用户
     * @param id 用户id
     * @return 如果有，返回
     */
    User findUserById(Integer id);

    /**
     * 更新头像
     * @param name 用户名
     * @param userId id
     * @return 1 is ok
     */
    int updateAva(String name, Integer userId);

    /**
     * 更新状态的
     * @param status 状态
     * @param userId 用户id
     * @return 1 is ok
     */
    int updateStatus(Integer status, Integer userId);

}
