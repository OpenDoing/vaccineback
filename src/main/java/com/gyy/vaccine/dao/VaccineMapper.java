package com.gyy.vaccine.dao;

import com.gyy.vaccine.entity.Vaccine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VaccineMapper{

    /**
     * 通过疫苗名称以及批号查询
     * @param pname 疫苗名称
     * @param bnumber 批号
     * @return 疫苗
     */
    Vaccine findVaccineByPnameAndBnumber(String pname, String bnumber);

    /**
     * 更新疫苗
     * @param pname 疫苗厂家
     * @param rname 疫苗名称
     * @param bnumber 批号
     * @param recall 召回
     * @param id 标识
     * @return 1 is ok
     */
    int updateVac(String pname, String rname, String bnumber, Integer recall, Integer id);

}
