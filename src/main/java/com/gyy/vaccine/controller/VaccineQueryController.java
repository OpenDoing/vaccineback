package com.gyy.vaccine.controller;

import com.gyy.vaccine.entity.Article;
import com.gyy.vaccine.entity.Vaccine;
import com.gyy.vaccine.repository.VaccineRepo;
import com.gyy.vaccine.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/vquery")
@CrossOrigin
public class VaccineQueryController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VaccineRepo vaccineRepo;

    @GetMapping("/net")
    public String get(@RequestParam String pname, @RequestParam String bnumber){
        String url = "https://m.baidu.com/zhuanjia/vaccinequery?product_name=" + pname + "&batch_number=" + bnumber;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    @GetMapping("/local")
    public Object getLocal(@RequestParam String pname, @RequestParam String bnumber){
        return ResponseUtil.ok(vaccineRepo.findVaccineByPnameAndBnumber(pname,bnumber ));
    }

    @PostMapping("/add")
    public Object addVaccine(@RequestBody Vaccine vaccine) {
        vaccineRepo.save(vaccine);
        return ResponseUtil.ok();
    }

    @PostMapping("/update")
    public Object updateVaccine(@RequestParam String pname,@RequestParam String rname,
                                @RequestParam String bnumber, @RequestParam Integer recall,
                                @RequestParam Integer id) {
        vaccineRepo.updateVac(pname, rname, bnumber, recall, id);
        return ResponseUtil.ok();
    }

    @DeleteMapping("/del")
    public Object delVaccine(@RequestParam Integer id) {
        vaccineRepo.deleteById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/all")
    public Object getAllVaccine() {
        return ResponseUtil.ok(vaccineRepo.findAll());
    }

}
