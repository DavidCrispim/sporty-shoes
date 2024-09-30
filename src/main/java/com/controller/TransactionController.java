package com.controller;

import com.entity.Login;
import com.entity.Product;
import com.entity.Transaction;
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
import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "placeTransaction/{pid}",method = RequestMethod.POST)
    public String placeTransaction(Product pp, Model mm, @PathVariable("pid") int pid) {
        if (transactionService.placeTransaction(pid, pp.getQuantity())) {
            mm.addAttribute("msgTransactionStatusSuccess","Order placed!");
        } else {
            mm.addAttribute("msgTransactionStatusError","An error occurred!");
        }

        mm.addAttribute("buttonText", "Search");
        mm.addAttribute("product_order", new Product());
        mm.addAttribute("products", productService.findAll());
        return "customer";
    }

    @RequestMapping(value = "findTransactionsByCategoryAndDate",method = RequestMethod.GET)
    public String findTransactionsByCategoryAndDate(Model mm, @RequestParam("category") Optional<String> category, @RequestParam("date") Optional<String> date) {
        mm.addAttribute("users", transactionService.findTransactionsByCategoryAndDate(category.orElse(""), date.orElse("")));
        mm.addAttribute("product_mgmt", new Product());
        mm.addAttribute("login_updt", new Login());
        mm.addAttribute("login_search", new Login());
        mm.addAttribute("products", productService.findAll());
        return "admin";
    }
}
