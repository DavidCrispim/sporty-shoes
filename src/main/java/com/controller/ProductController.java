package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Product;
import com.entity.Login;
import com.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "addProduct",method = RequestMethod.POST)
    public String addProduct(Login ll, Product pp, Model mm, @RequestParam("pButton") String buttonValue) {
        if(buttonValue.equals("Add Product")) {
            if( productService.storeProduct(pp)) {
                mm.addAttribute("msgProductMgmtSuccess","Product Added!");
            } else {
                mm.addAttribute("msgProductMgmtError","An error occurred!");
            }
            mm.addAttribute("buttonText", "Add Product");
            mm.addAttribute("product", new Product());

        }else {
           if(productService.updateProduct(pp)) {
                mm.addAttribute("msgProductMgmtSuccess","Product Updated!");
           } else {
                mm.addAttribute("msgProductMgmtError","An error occurred!");
           }
        }

        mm.addAttribute("buttonText", "Add Product");
        mm.addAttribute("product", new Product());
        mm.addAttribute("products", productService.findAll());

        return "admin";
    }

    @RequestMapping(value = "deleteProduct",method = RequestMethod.GET)
    public String deleteProduct(Login ll, Product pp, Model mm, @RequestParam("pid") int pid) {
        mm.addAttribute("buttonText", "Add Product");

        if(productService.deleteProduct(pid)) {
            mm.addAttribute("msgProductMgmtSuccess","Product Deleted!");
        } else {
            mm.addAttribute("msgProductMgmtError","An error occurred!");
        }

        mm.addAttribute("product", new Product());
        mm.addAttribute("buttonText", "Add Product");
        mm.addAttribute("products", productService.findAll());
        return "admin";
    }

    @RequestMapping(value = "getProductInfo",method = RequestMethod.GET)
    public String getProductInfo(Login ll, Product pp, Model mm, @RequestParam("pid") int pid) {
    	pp = productService.findProduct(pid);
    	
    	mm.addAttribute("product", pp);
        mm.addAttribute("buttonText", "Update Product");

        mm.addAttribute("products", productService.findAll());
        return "admin";
    }
}