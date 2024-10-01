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
            "(:pname = \"\" or p.pname = :pname)" +
            "and (:type = \"\" or p.type = :type)" +
            "and (:brand = \"\" or p.brand = :brand)" +
        	"and (:price = 0.0 or p.price <= :price)"

    )
    public List<Product> filterProducts(@Param("pname") String pname, @Param("type") String type, @Param("brand") String brand, @Param("price") float price);

}
