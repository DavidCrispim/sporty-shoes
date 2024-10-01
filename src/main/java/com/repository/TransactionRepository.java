package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

   /* @Query("select t from Transaction t where " +
            "(:category is null or p.product.type = :category)" +
            "and (:dateTime is null or p.date = :dateTime)")
    public List<Transaction> findByCategoryAndDate(String category, LocalDateTime dateTime);*/
}
