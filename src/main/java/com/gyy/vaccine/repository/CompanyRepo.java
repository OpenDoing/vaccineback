package com.gyy.vaccine.repository;

import com.gyy.vaccine.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer>{

    Company findCompanyById(Integer id);
}
