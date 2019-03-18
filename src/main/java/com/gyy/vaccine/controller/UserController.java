package com.gyy.vaccine.controller;

import com.gyy.vaccine.entity.User;
import com.gyy.vaccine.repository.UserRepo;
import com.gyy.vaccine.service.UserService;
import com.gyy.vaccine.util.JacksonUtil;
import com.gyy.vaccine.util.ResponseUtil;
import com.gyy.vaccine.util.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Value("${imgpath}")
    private String filePath;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody String body){
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return ResponseUtil.badArgument();
        }
        User user = userRepo.findUserByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password, user.getPassword())){
            return ResponseUtil.fail(403, "账号密码不对");
        }
        return ResponseUtil.ok(user);
    }

    @PostMapping("/register")
    public Object reg(@RequestBody User user) {

        User user1 = userRepo.findUserByUsername(user.getUsername());
        if(user1 != null){
            return ResponseUtil.regerr();
        }
        user.setStatus(1);
        user.setAvatar("default.png");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseUtil.ok(user);
    }

    @PostMapping(value = "/avatar")
    public Object fileUpload(@RequestParam(value = "file") MultipartFile file, Integer userId) {
        if (file.isEmpty()) {
            return ResponseUtil.fail(404, "文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名

        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtil.fail(-1,"上传失败");
        }
        System.out.println(dest.getName());
        System.out.println(userId);
        userRepo.updateAva(dest.getName(),userId);

        return ResponseUtil.ok("上传成功",dest.getName());
    }

    @PostMapping("/password")
    public Object changePassword(@RequestParam Integer userId, @RequestParam String  opassword,@RequestParam String npassword) {
        if (userService.ChangePassword(userId, opassword, npassword).equals("OK")){
            return ResponseUtil.ok("修改成功");
        }else {
            return ResponseUtil.fail(-1, "原密码不匹配");
        }
    }

    @GetMapping("/get")
    public Object getUser(@RequestParam Integer userId) {
        return ResponseUtil.ok(userRepo.findUserById(userId));
    }

    @GetMapping("/users")
    public Object getUsers() {
        return ResponseUtil.ok(userRepo.findAll());
    }

    @PostMapping("/fen")
    private Object userFen(@RequestParam Integer userId, @RequestParam Integer status) {

        userRepo.updateStatus(status,userId);
        return ResponseUtil.ok();
    }

}
