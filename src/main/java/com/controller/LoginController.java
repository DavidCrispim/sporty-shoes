package com.controller;

import com.entity.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String openLoginPage(Login ll, Model mm) {
        mm.addAttribute("login_signin", ll);
        mm.addAttribute("login_signup", ll);
        return "login";
    }

    @RequestMapping(value = "signIn",method = RequestMethod.POST)
    public String signIn(Login ll, Model mm) {

        String result = loginService.signIn(ll);
        if(result.equals("admin")) {
            ll.setPassword("");
            mm.addAttribute("login", ll);

            return "admin";
        }else if(result.equals("customer")){
            return "customer";
        } else {
            ll.setEmailid("");
            ll.setPassword("");
            mm.addAttribute("login_signin", ll);
            mm.addAttribute("login_signup", ll);
            mm.addAttribute("msgSignInError","Sign In failed");
            return "login";
        }

    }

    @RequestMapping(value = "signUp",method = RequestMethod.POST)
    public String signUp(Login ll, Model mm) {

        ll.setType("customer");
        if(loginService.signUp(ll)) {
            mm.addAttribute("msgSignUpSuccess","Sign Up success");
        }else {
            mm.addAttribute("msgSignUpError","Sign Up failed");
        }

        ll.setEmailid("");
        ll.setPassword("");

        mm.addAttribute("login_signin", ll);
        mm.addAttribute("login_signup", ll);

        return "login";
    }
}


