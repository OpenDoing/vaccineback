package com.gyy.vaccine.repository;

import com.gyy.vaccine.entity.Baby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public interface BabyRepo extends JpaRepository<Baby,Integer>{

    @Modifying
    @Transactional
    @Query(value = "update baby set byname= ?1, vname=?2, bnumber=?3, itime=?4 where id= ?5", nativeQuery = true)
    int updateBaby(String byname, String vname, String bnumber, String itime, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update baby set company= ?2 where id= ?1", nativeQuery = true)
    int updateComp(Integer uid, String name);

    Baby findBabyById(Integer id);

    List<Baby> findAll();
}
