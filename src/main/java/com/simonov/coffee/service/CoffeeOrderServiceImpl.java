package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.model.CoffeeOrderItem;
import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.repository.CoffeeOrderItemRepository;
import com.simonov.coffee.repository.CoffeeOrderRepository;
import com.simonov.coffee.repository.CoffeeTypeRepository;
import com.simonov.coffee.to.CoffeeOrderItemTo;
import com.simonov.coffee.to.Order;
import com.simonov.coffee.utill.BusinessRules;
import com.simonov.coffee.utill.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService {

    private CoffeeOrderRepository coffeeOrderRepository;
    private BusinessRules businessRules;
    private CoffeeTypeRepository coffeeTypeRepository;
    private CoffeeOrderItemRepository coffeeOrderItemRepository;

    @Autowired
    public CoffeeOrderServiceImpl(CoffeeOrderRepository coffeeOrderRepository,
                                  BusinessRules businessRules,
                                  CoffeeTypeRepository coffeeTypeRepository,
                                  CoffeeOrderItemRepository coffeeOrderItemRepository) {
        this.coffeeOrderRepository = coffeeOrderRepository;
        this.businessRules = businessRules;
        this.coffeeTypeRepository = coffeeTypeRepository;
        this.coffeeOrderItemRepository = coffeeOrderItemRepository;
    }

    @Override
    public List<CoffeeType> getAllEnabledCoffeType() {
        return Collections.unmodifiableList(coffeeTypeRepository.getAllEnabled());
    }

    @Override
    public Order create(CoffeeOrder coffeeOrder, List<CoffeeOrderItemTo> coffeeTypeList) {

        return null;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public CoffeeOrder getOne(int id) {
        return ValidationUtil.checkNotFoundWithId(coffeeOrderRepository.getOneById(id), id);
    }


    @Override
    public List<CoffeeOrderItemTo> getByOrderId(int id) {
        List<CoffeeOrderItem> allByTypeId = coffeeOrderItemRepository.findAllByCoffeeOrderId(id);
        List<CoffeeOrderItemTo> collect = allByTypeId.stream().
                map(x -> new CoffeeOrderItemTo(x.getCoffeeType().getTypeName(), x.getQuantity(), x.getCoffeeType().getPrice(),
                        businessRules.calculateSubTotalCost(x.getQuantity(), x.getCoffeeType().getPrice())))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(collect);
    }

    @Override
    public List<CoffeeOrderItemTo> getByCoffeeTypeIdAndQuantity(Map<Integer, Integer> ctm) {
        List<CoffeeOrderItemTo> resultList =
                coffeeTypeRepository.getAllByIds(ctm.keySet()).stream()
                        .map(ct -> new CoffeeOrderItemTo(ct.getId(),
                                ct.getTypeName(),
                                ctm.get(ct.getId()),
                                ct.getPrice()))
                        .collect(Collectors.toList());
        resultList.forEach(entry -> entry.setTotal(businessRules.calculateSubTotalCost(entry)));
        return Collections.unmodifiableList(resultList);
    }
    @Override
    public Order prepareOrder(List<CoffeeOrderItemTo> items) {
        double subtotal = businessRules.calculateOrderSubTotal(items);
        double delivery = businessRules.calculateDelivery(subtotal);
        return new Order(items, subtotal, delivery, subtotal + delivery);
    }
}
