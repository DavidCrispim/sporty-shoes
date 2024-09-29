package com.service;

import com.repository.LoginRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entity.Login;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    @PostConstruct
    public void init(){
        Login ll = new Login();
        ll.setEmailid("admin@gmail.com");
        ll.setPassword("admin123");
        ll.setType("admin");
        loginRepository.save(ll);
    }

    public String signIn(Login login) {
        Optional<Login> result = loginRepository.findByEmailAndPassword(login.getEmailid(), login.getPassword());
        if(result.isPresent()) {
            Login l = result.get();
            return l.getType();
        }else {
            return "error";
        }
    }

    public Boolean signUp(Login login) {
        Optional<Login> result = loginRepository.findByEmail(login.getEmailid());
        if(result.isPresent()) {
            return false;
        }else {
            loginRepository.save(login);
            return true;
        }
    }
}