package com.gyy.vaccine.repository;

import com.gyy.vaccine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);
    User findUserById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update user set avatar= ?1 where id= ?2", nativeQuery = true)
    int updateAva(String name, Integer userId);

    @Modifying
    @Transactional
    @Query(value = "update user set status= ?1 where id= ?2", nativeQuery = true)
    int updateStatus(Integer status, Integer userId);

}
