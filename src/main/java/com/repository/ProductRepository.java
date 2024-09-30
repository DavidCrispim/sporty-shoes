package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("select p from Product p where p.pname = :pname")
    public Optional<Product> findProductByName(@Param("pname") String pname);

    @Query("select p from Product p where " +
            "(:name is null or p.pname = :name)" +
            "and (:type is null or p.type = :type)" +
            "and (:brand is null or p.brand = :brand)" +
            "and (:price is null or p.price = :price)"
    )
    public List<Product> filterProducts(String name, String type, String brand, float price);

}
