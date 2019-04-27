package com.gyy.vaccine.dao;

import com.gyy.vaccine.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    /**
     * 通过id查询公司信息
     * @param id id
     * @return 公司信息
     */
    Company findCompanyById(Integer id);

    /**
     * 更新公司信息
     * @param name 公司名称
     * @param introduce 公司介绍
     * @param userId 用户id
     * @return 1 is ok
     */
    int updateCompany(String name, String introduce, Integer userId);

}
