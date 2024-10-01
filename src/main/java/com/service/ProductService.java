package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Product;
import com.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Boolean storeProduct(Product product) {
    	try {
    		Optional<Product> result = productRepository.findProductByName(product.getPname());
    	    if(result.isPresent()) {
    	    	return false;
    	    }else {
    	    	productRepository.save(product);
    	        return true;
    	    }
    	} catch (Exception e) {
    		return false;
    	}
       
    }

    public Boolean updateProduct(Product product) {
    	try {
    		Optional<Product> result = productRepository.findById(product.getPid());	
            if(result.isPresent()) {
                Product p = result.get();
                p.setPname(product.getPname());
                p.setType(product.getType());
                p.setBrand(product.getBrand());
                p.setImage(product.getImage());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());
                productRepository.saveAndFlush(p);
                return true;
            }else {
                return false;
            }
    	} catch (Exception e) {
    		return false;
    	}
        
    }

    public Boolean deleteProduct(int pid) {
    	try {
    		Optional<Product> product = productRepository.findById(pid);
    	    if(product.isPresent()) {
    	    	productRepository.deleteById(pid);
    	        return true;
    	    }else {
    	    	return false;
    	    }
    	} catch (Exception e) {
    		return false;
    	}
    }

    public Product findProduct(int pid) {
        Optional<Product> result = productRepository.findById(pid);
        if(result.isPresent()) {
            return result.get();
        }else {
            return new Product();
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> filterProducts(Product product) {
        return productRepository.filterProducts(product.getPname(), product.getType(), product.getBrand(), product.getPrice());
    }
}
