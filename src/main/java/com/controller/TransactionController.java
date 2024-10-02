package com.controller;

import com.entity.Product;
import com.entity.Transaction;
import com.service.LoginService;
import com.service.ProductService;
import com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    ProductService productService;
    
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "placeTransaction/{pid}",method = RequestMethod.POST)
    public String placeTransaction(Transaction tt, Model mm, @PathVariable("pid") int pid) {
        if (transactionService.placeTransaction(pid, tt)) {
            mm.addAttribute("msgTransactionStatusSuccess","Order placed!");
        } else {
            mm.addAttribute("msgTransactionStatusError","An error occurred!");
        }

        mm.addAttribute("product_filter", new Product());     
        mm.addAttribute("products", productService.findAll());
        return "customer";
    }

    @RequestMapping(value = "findTransactionsByCategoryAndDate",method = RequestMethod.GET)
    public String findTransactionsByCategoryAndDate(Model mm, @RequestParam(required = false) String category, @RequestParam(required = false) String date) {
        if(category.isEmpty() && date.isEmpty() ) {
            mm.addAttribute("transactions", transactionService.findAll());
        } else {
            LocalDateTime tDateTime = null;
        	LocalDateTime tDateTimePlus1 = null;
        	
            if(!date.isEmpty()) {
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                tDateTime = LocalDate.parse(date, formatter).atStartOfDay();
            	tDateTimePlus1 = tDateTime.plusDays(1);
            }
            
            mm.addAttribute("transactions", transactionService.findTransactionsByCategoryAndDate(category, tDateTime, tDateTimePlus1));
        }

        mm.addAttribute("product_mgmt", new Product());
        mm.addAttribute("buttonText", "Add Product");
        mm.addAttribute("products", productService.findAll());
        mm.addAttribute("users", loginService.findAll());
        return "admin";
    }
}
