package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CoffeeOrderItem;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Profile("spring-data-jpa")
public interface CrudCoffeeOrderItemRepository extends JpaRepository<CoffeeOrderItem,Integer> {

    @Query("SELECT o FROM CoffeeOrderItem o WHERE o.coffeeOrder.id=?1")
    List<CoffeeOrderItem> findAllByCoffeeOrderId(int id);
}
