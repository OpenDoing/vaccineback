package com.gyy.vaccine.repository;

import com.gyy.vaccine.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface VaccineRepo extends JpaRepository<Vaccine,Integer> {

    Vaccine findVaccineByPnameAndBnumber(String pname, String bnumber);

    @Modifying
    @Transactional
    @Query(value = "update vaccine set pname= ?1, rname=?2, bnumber=?3, recall=?4 where id= ?5", nativeQuery = true)
    int updateVac(String pname, String rname, String bnumber,Integer recall, Integer id);

}
