package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("select p from Product p where p.pname > :pname")
    public Optional<Product> findProductByName(@Param("pname") String pname);

}
