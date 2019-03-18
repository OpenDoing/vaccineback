package com.gyy.vaccine.controller;

import com.gyy.vaccine.entity.Baby;
import com.gyy.vaccine.entity.Company;
import com.gyy.vaccine.repository.CompanyRepo;
import com.gyy.vaccine.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepo companyRepo;

    @PostMapping("/add")
    public Object addBaby(@RequestBody Company company) {
        return ResponseUtil.ok(companyRepo.save(company));
    }

    @DeleteMapping("/del")
    public Object delBaby(@RequestParam Integer id) {
        companyRepo.deleteById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/get")
    public Object getOneBaby(@RequestParam Integer id) {
        return ResponseUtil.ok(companyRepo.findById(id));
    }

    @GetMapping("/all")
    public Object getAllBaby() {
        return ResponseUtil.ok(companyRepo.findAll());
    }
}
