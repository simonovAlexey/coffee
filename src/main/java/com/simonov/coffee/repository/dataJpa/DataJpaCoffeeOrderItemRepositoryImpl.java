package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CoffeeOrderItem;
import com.simonov.coffee.repository.CoffeeOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("spring-data-jpa")
@Repository
public class DataJpaCoffeeOrderItemRepositoryImpl implements CoffeeOrderItemRepository {

    private CrudCoffeeOrderItemRepository coffeeOrderItemRepo;
    private DataJpaCoffeeTypeRepository coffeeTypeRepo;

    @Autowired
    public DataJpaCoffeeOrderItemRepositoryImpl(CrudCoffeeOrderItemRepository coffeeOrderItemRepo, DataJpaCoffeeTypeRepository coffeeTypeRepo) {
        this.coffeeOrderItemRepo = coffeeOrderItemRepo;
        this.coffeeTypeRepo = coffeeTypeRepo;
    }

    @Override
    public List<CoffeeOrderItem> findAllByCoffeeOrderId(int id) {
        return coffeeOrderItemRepo.findAllByCoffeeOrderId(id);
    }


    @Override
    @Transactional
    public CoffeeOrderItem save(CoffeeOrderItem coffeeOrderItem, int typeId) {
        if (!coffeeOrderItem.isNew() && get(coffeeOrderItem.getId(), typeId) == null) {
            return null;
        }
        coffeeOrderItem.setCoffeeType(coffeeTypeRepo.getOne(typeId));
        return coffeeOrderItemRepo.save(coffeeOrderItem);
    }

    @Override
    public CoffeeOrderItem get(int id, int typeId) {
        CoffeeOrderItem orderItem = coffeeOrderItemRepo.findOne(id);
        return orderItem != null && orderItem.getCoffeeType().getId() == typeId ? orderItem : null;
    }
}
