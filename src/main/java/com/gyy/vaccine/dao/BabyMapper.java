package com.gyy.vaccine.dao;

import com.gyy.vaccine.entity.Baby;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BabyMapper {

    /**
     * 更新宝宝信息
     * @param byname 宝贝名字
     * @param vname 疫苗名称
     * @param bnumber 疫苗批号
     * @param itime 疫苗时间
     * @param id 疫苗id
     * @return 1 is ok
     */
    int updateBaby(String byname, String vname, String bnumber, String itime, Integer id);

    /**
     * 更新公司信息
     * @param uid userId
     * @param name 公司名
     * @return 1 is ok
     */
    int updateComp(Integer uid, String name);

    /**
     * 通过Id查找宝贝
     * @param id  Id
     * @return 宝贝
     */
    Baby findBabyById(Integer id);

    /**
     * 查询所有宝贝
     * @return 所有宝贝
     */
    List<Baby> findAll();

    /**
     * 通过id查询所有宝贝
     * @param uid 宝贝id
     * @return 符合条件的所有宝贝
     */
    List<Baby> findBabiesByUid(Integer uid);
}
