package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Item;
import com.example.postgresdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {



    @Query(value = "select o.items from Order o where o.id = :orderId")
    List<Item> findAllItemsByOrder(@Param("orderId") Long orderId);

    @Modifying
    @Transactional
    @Query(value = "update orders set status = 2 where id = :orderId", nativeQuery = true)
    void cancelOrder(@Param("orderId") Long orderId);

}
