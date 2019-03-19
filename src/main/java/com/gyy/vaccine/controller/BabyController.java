package com.gyy.vaccine.controller;

import com.gyy.vaccine.entity.Baby;
import com.gyy.vaccine.entity.Vaccine;
import com.gyy.vaccine.repository.BabyRepo;
import com.gyy.vaccine.repository.CompanyRepo;
import com.gyy.vaccine.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/baby")
public class BabyController {

    @Autowired
    private BabyRepo babyRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @PostMapping("/add")
    public Object addBaby(@RequestBody Baby baby) {
        Calendar cS = Calendar.getInstance();
        cS.setTime(baby.getItime());
        cS.add(Calendar.DAY_OF_MONTH, 1);
        baby.setItime(cS.getTime());
        babyRepo.save(baby);
        return ResponseUtil.ok();
    }

    @PostMapping("/update")
    public Object updateBaby(@RequestParam String byname, @RequestParam String vname,
                             @RequestParam String itime, @RequestParam String bnumber,
                             @RequestParam Integer id) {
        babyRepo.updateBaby(byname, vname, bnumber, itime, id);
        return ResponseUtil.ok();
    }

    @PostMapping("/updateCompany")
    public Object updateBaby(@RequestParam Integer uid, @RequestParam Integer cid) {
        String company = companyRepo.findCompanyById(cid).getName();

        babyRepo.updateComp(uid, company);
        return ResponseUtil.ok();
    }

    @DeleteMapping("/del")
    public Object delBaby(@RequestParam Integer id) {
        babyRepo.deleteById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/get")
    public Object getOneBaby(@RequestParam Integer id) {
        Baby baby = babyRepo.findBabyById(id);
        Calendar cS = Calendar.getInstance();
        cS.setTime(baby.getItime());
        cS.add(Calendar.DAY_OF_MONTH, 1);
        baby.setItime(cS.getTime());
        return ResponseUtil.ok(baby);
    }

    @GetMapping("/getSomeone")
    public Object getSomeone(@RequestParam Integer uid) {
        List<Baby> babies = babyRepo.findBabiesByUid(uid);
        for (Baby baby:babies){
            Calendar cS = Calendar.getInstance();
            cS.setTime(baby.getItime());
            cS.add(Calendar.DAY_OF_MONTH, 1);
            baby.setItime(cS.getTime());
        }
        return ResponseUtil.ok(babies);
    }

    @GetMapping("/all")
    public Object getAllBaby() {
        List<Baby> babies = babyRepo.findAll();
        for (Baby baby:babies){
            Calendar cS = Calendar.getInstance();
            cS.setTime(baby.getItime());
            cS.add(Calendar.DAY_OF_MONTH, 1);
            baby.setItime(cS.getTime());
        }
        return ResponseUtil.ok(babies);
    }
}
