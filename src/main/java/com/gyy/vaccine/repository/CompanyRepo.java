package com.gyy.vaccine.repository;

import com.gyy.vaccine.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer>{

    Company findCompanyById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update company set name= ?1,introduce=?2 where id= ?3", nativeQuery = true)
    int updateCompany(String name, String introduce, Integer userId);
}
