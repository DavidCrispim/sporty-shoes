package com.controller;

import com.entity.Login;
import com.entity.Product;

import com.service.TransactionService;
import com.service.ProductService;
import com.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    ProductService productService;

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String openLoginPage(Model mm) {
        return "login";
    }

    @RequestMapping(value = "signIn",method = RequestMethod.POST)
    public String signIn(Login ll, Model mm) {

        String result = loginService.signIn(ll);
        if(result.equals("admin")) {
            mm.addAttribute("product_mgmt", new Product());
            mm.addAttribute("buttonText", "Add Product");

            mm.addAttribute("products", productService.findAll());
            mm.addAttribute("users", loginService.findAll());
            mm.addAttribute("transactions", transactionService.findAll());
            return "admin";
        }else if(result.equals("customer")){
            mm.addAttribute("product_filter", new Product());
            mm.addAttribute("products", productService.findAll());
            return "customer";
        } else {
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

        return "login";
    }

    @RequestMapping(value = "updateAdminPassword",method = RequestMethod.POST)
    public String updateAdminPassword(Model mm, Login login){
        if(loginService.updateAdminPassword(login.getPassword())){
            mm.addAttribute("msgPasswordUpdtSuccess","Password update success!");
        }else {
            mm.addAttribute("msgPasswordUpdtError","Password update failed!");
        }

        mm.addAttribute("product_mgmt", new Product());
        mm.addAttribute("buttonText", "Add Product");
        mm.addAttribute("products", productService.findAll());

        return "admin";
    }

    @RequestMapping(value = "findUser",method = RequestMethod.GET)
    public String findUser(Model mm, @RequestParam(required = false) String emailid){
        mm.addAttribute("buttonText", "Add Product");
        mm.addAttribute("product_mgmt", new Product());
        mm.addAttribute("products", productService.findAll());

        if(emailid.isEmpty()){
            mm.addAttribute("users", loginService.findAll());
        } else {
            mm.addAttribute("users", loginService.findUserByEmail(emailid));
        }

        return "admin";
    }
}


