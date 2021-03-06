package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.model.CoffeeOrderItem;
import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.repository.CoffeeOrderItemRepository;
import com.simonov.coffee.repository.CoffeeOrderRepository;
import com.simonov.coffee.repository.CoffeeTypeRepository;
import com.simonov.coffee.to.OrderTO;
import com.simonov.coffee.to.TypeToSelected;
import com.simonov.coffee.to.TypeToSelectedWraper;
import com.simonov.coffee.util.BusinessRules;
import com.simonov.coffee.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService {
    private static final Logger LOG = LoggerFactory.getLogger(CoffeeOrderServiceImpl.class);

    private CoffeeOrderRepository orderRepository;
    private BusinessRules businessRules;
    private CoffeeTypeRepository typeRepository;
    private CoffeeOrderItemRepository orderItemRepository;

    @Autowired
    public CoffeeOrderServiceImpl(CoffeeOrderRepository orderRepository,
                                  BusinessRules businessRules,
                                  CoffeeTypeRepository typeRepository,
                                  CoffeeOrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.businessRules = businessRules;
        this.typeRepository = typeRepository;
        this.orderItemRepository = orderItemRepository;
    }

    private List<CoffeeType> getAllEnabledCoffeType() {
        return Collections.unmodifiableList(typeRepository.getAllEnabled());
    }

    @Override
    public TypeToSelectedWraper getWrapper() {
        List<TypeToSelected> converted = getAllEnabledCoffeType().stream().map(i -> new TypeToSelected(i.getId(), i.getTypeName(), i.getPrice())).collect(Collectors.toList());
        return new TypeToSelectedWraper(converted);
    }

    @Override
    @Transactional
    public List<CoffeeOrderItem> save(OrderTO orderTO) {
        CoffeeOrder coffeeOrder = new CoffeeOrder(LocalDateTime.now(), orderTO.getName(), orderTO.getDeliveryAdress(), orderTO.getTotal());
        CoffeeOrder savedOrder = orderRepository.save(coffeeOrder);
        List<CoffeeOrderItem> result = new ArrayList<>();
        for (TypeToSelected item : orderTO.getItems()) {
            CoffeeOrderItem coffeeOrderItem = new CoffeeOrderItem(savedOrder, item.getQuantity());
            result.add(orderItemRepository.save(coffeeOrderItem, item.getId()));
        }
        LOG.debug("Order saved: ", result);
        return result;
    }

    @Override
    public CoffeeOrder getOne(int id) {
        CoffeeOrder coffeeOrder = orderRepository.findById(id);
        if (coffeeOrder==null) throw new NotFoundException("Not found entity with id=" + id);
        return coffeeOrder;
    }


    @Override
    public OrderTO prepareOrder(List<TypeToSelected> items) {
        double subtotal = 0;
        int eachNcupFree = businessRules.getNFreeCup();
        for (TypeToSelected item : items) {
            double itemSTotal = businessRules.calculateSubTotalCost(item.getQuantity(), item.getPrice());
            subtotal += itemSTotal;
            item.setTotal(itemSTotal);
            if (item.getQuantity() < eachNcupFree) item.setSelected(false);

        }
        double delivery = businessRules.calculateDelivery(subtotal);
        return new OrderTO(items, subtotal, delivery, subtotal + delivery);
    }

    @Override
    public int getNFreeCup() {
        return businessRules.getNFreeCup();
    }
}
