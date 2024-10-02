package com.service;

import com.entity.Product;
import com.entity.Transaction;
import com.repository.ProductRepository;
import com.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ProductRepository productRepository;

    public Boolean placeTransaction(int pid , Transaction t) {
        if (t.getQuantity() == 0){
            return false;
        }
      
        Optional<Product> result = productRepository.findById(pid);
        if(result.isPresent()) {
            Product p = result.get();
            if(t.getQuantity() <= p.getQuantity()) {
                p.setQuantity(p.getQuantity()-t.getQuantity());
                productRepository.saveAndFlush(p);
                
                t.setQuantity(t.getQuantity());
                t.settDateTime(LocalDateTime.now());
                t.setValue(t.getQuantity() * p.getPrice());
                t.setProduct(p);
                transactionRepository.save(t);
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findTransactionsByCategoryAndDate(String type, LocalDateTime tDateTime, LocalDateTime tDateTimePlus1) {  
        return transactionRepository.findByCategoryAndDate(type, tDateTime, tDateTimePlus1);
    }
}
