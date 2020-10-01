package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select id from product where id in :range", nativeQuery = true)
    List<Long> findIdInRanges(@Param("range") Set<Long> range);

}
