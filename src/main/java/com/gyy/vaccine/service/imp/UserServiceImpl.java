package com.gyy.vaccine.service.imp;

import com.gyy.vaccine.entity.User;
import com.gyy.vaccine.repository.UserRepo;
import com.gyy.vaccine.service.UserService;
import com.gyy.vaccine.util.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public String ChangePassword(Integer userId, String opassword, String npassword) {
        User user = userRepo.findUserById(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(opassword, user.getPassword())){
            user.setPassword(encoder.encode(npassword));
            userRepo.save(user);
            return "OK";
        } else {
            return "ERROR";
        }
    }
}
