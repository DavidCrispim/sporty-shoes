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
	
	@Query("select t from Transaction t inner join t.product p where " + 
            "(:type = \"\" or p.type = :type)" +
			"and (:tDateTime is null or t.tDateTime between :tDateTime and :tDateTimePlus1)")
    public List<Transaction> findByCategoryAndDate(
    		@Param("type") String type, 
    		@Param("tDateTime") LocalDateTime tDateTime,
    		@Param("tDateTimePlus1") LocalDateTime tDateTimePlus1);
    
    
    
}
