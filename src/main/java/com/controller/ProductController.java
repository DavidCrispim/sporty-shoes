package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Product;
import com.entity.Login;
import com.service.LoginService;
import com.service.ProductService;
import com.service.TransactionService;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    LoginService loginService;
    
    @Autowired
    TransactionService transactionService;
    
    @RequestMapping(value = "addProduct",method = RequestMethod.POST)
    public String addProduct(Product pp, Model mm, @RequestParam("pButton") String buttonValue) {
        if(buttonValue.equals("Add Product")) {
            if( productService.storeProduct(pp)) {
                mm.addAttribute("msgProductMgmtSuccess","Product Added!");
            } else {
                mm.addAttribute("msgProductMgmtError","An error occurred!");
            }
        }else {
           if(productService.updateProduct(pp)) {
                mm.addAttribute("msgProductMgmtSuccess","Product Updated!");
           } else {
                mm.addAttribute("msgProductMgmtError","An error occurred!");
           }
        }

        mm.addAttribute("buttonText", "Add Product");
        mm.addAttribute("product_mgmt", new Product());
        mm.addAttribute("products", productService.findAll());
        mm.addAttribute("transactions", transactionService.findAll());
        mm.addAttribute("users", loginService.findAll());

        return "admin";
    }

    @RequestMapping(value = "deleteProduct",method = RequestMethod.GET)
    public String deleteProduct(Model mm, @RequestParam("pid") int pid) {
        if(productService.deleteProduct(pid)) {
            mm.addAttribute("msgProductMgmtSuccess","Product Deleted!");
        } else {
            mm.addAttribute("msgProductMgmtError","An error occurred!");
        }

        mm.addAttribute("product_mgmt", new Product());
        mm.addAttribute("buttonText", "Add Product");
        mm.addAttribute("products", productService.findAll());
        mm.addAttribute("transactions", transactionService.findAll());
        mm.addAttribute("users", loginService.findAll());
        return "admin";
    }

    @RequestMapping(value = "getProductInfo",method = RequestMethod.GET)
    public String getProductInfo(Model mm, @RequestParam("pid") int pid) {
    	Product pp = productService.findProduct(pid);
    	
    	mm.addAttribute("product_mgmt", pp);
        mm.addAttribute("buttonText", "Update Product");

        mm.addAttribute("products", productService.findAll());
        mm.addAttribute("transactions", transactionService.findAll());
        mm.addAttribute("users", loginService.findAll());
        return "admin";
    }

    @RequestMapping(value = "filterProducts",method = RequestMethod.GET)    	
    public String filterProducts(Model mm, Product pp) {
    	if(pp.getPname().isEmpty() && pp.getType().isEmpty() && pp.getBrand().isEmpty() && pp.getPrice() == 0.0) {
            mm.addAttribute("products", productService.findAll());
        } else {         
            mm.addAttribute("products", productService.filterProducts(pp));
        }
    	 mm.addAttribute("product_filter", pp);

        return "customer";
    }
}