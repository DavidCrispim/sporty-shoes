package com.service;

import com.entity.Product;
import com.entity.Transaction;
import com.repository.ProductRepository;
import com.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ProductRepository productRepository;

    public Boolean placeTransaction(int pid , int transactionQuantity) {
        if (transactionQuantity == 0){
            return false;
        }
        Transaction t = new Transaction();
        Optional<Product> result = productRepository.findById(pid);
        if(result.isPresent()) {
            Product p = result.get();
            if(transactionQuantity <= p.getQuantity()) {
                p.setQuantity(p.getQuantity()-transactionQuantity);
                productRepository.saveAndFlush(p);

                t.setTDateTime(LocalDateTime.now());
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
        //return transactionRepository.findAll();
        List<Transaction> transactions = new ArrayList<Transaction>();

        Transaction o = new Transaction();
        o.setTid(1);
        o.setTDateTime(LocalDate.of(2020, Month.JANUARY, 18).atStartOfDay());
        o.setQuantity(2);
        transactions.add(o);

        Transaction o2 = new Transaction();
        o2.setTid(2);
        o2.setTDateTime(LocalDate.of(2024, Month.FEBRUARY, 18).atStartOfDay());
        o2.setQuantity(4);
        transactions.add(o2);

        return transactions;
    }
}
