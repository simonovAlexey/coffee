package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.model.CoffeeOrderItem;
import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.repository.CoffeeOrderItemRepository;
import com.simonov.coffee.repository.CoffeeOrderRepository;
import com.simonov.coffee.repository.CoffeeTypeRepository;
import com.simonov.coffee.to.CoffeeOrderItemTo;
import com.simonov.coffee.to.OrderTO;
import com.simonov.coffee.utill.BusinessRules;
import com.simonov.coffee.utill.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService {

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

    @Override
    public List<CoffeeType> getAllEnabledCoffeType() {
        return Collections.unmodifiableList(typeRepository.getAllEnabled());
    }

    @Override
    public OrderTO create(CoffeeOrder coffeeOrder, List<CoffeeOrderItemTo> coffeeTypeList) {

        return null;
    }

    @Override
    @Transactional
    public List<CoffeeOrderItem> save(OrderTO orderTO) {
        CoffeeOrder coffeeOrder = new CoffeeOrder(LocalDateTime.now(), orderTO.getName(), orderTO.getDeliveryAdress(), orderTO.getTotal());
        CoffeeOrder savedOrder = orderRepository.save(coffeeOrder);
        List<CoffeeOrderItem> result = new ArrayList<>();
        for (CoffeeOrderItemTo item : orderTO.getItems()) {
            CoffeeOrderItem coffeeOrderItem = new CoffeeOrderItem(savedOrder, item.getQuantity());
            result.add(orderItemRepository.save(coffeeOrderItem, item.getId()));
        }
        return result;
    }

    @Override
    public CoffeeOrder getOne(int id) {
        return ValidationUtil.checkNotFoundWithId(orderRepository.findById(id), id);
    }


    @Override
    public List<CoffeeOrderItemTo> getByOrderId(int id) {
        List<CoffeeOrderItem> allByTypeId = orderItemRepository.findAllByCoffeeOrderId(id);
        List<CoffeeOrderItemTo> collect = allByTypeId.stream().
                map(x -> new CoffeeOrderItemTo(x.getCoffeeType().getTypeName(), x.getQuantity(), x.getCoffeeType().getPrice(),
                        businessRules.calculateSubTotalCost(x.getQuantity(), x.getCoffeeType().getPrice())))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(collect);
    }

    @Override
    public List<CoffeeOrderItemTo> getByCoffeeTypeIdAndQuantity(Map<Integer, Integer> ctm) {
        List<CoffeeOrderItemTo> resultList =
                typeRepository.getAllByIds(ctm.keySet()).stream()
                        .map(ct -> new CoffeeOrderItemTo(ct.getId(),
                                ct.getTypeName(),
                                ctm.get(ct.getId()),
                                ct.getPrice()))
                        .collect(Collectors.toList());
        resultList.forEach(entry -> entry.setTotal(businessRules.calculateSubTotalCost(entry)));
        return Collections.unmodifiableList(resultList);
    }

    @Override
    public OrderTO prepareOrder(List<CoffeeOrderItemTo> items) {
        double subtotal = businessRules.calculateOrderSubTotal(items);
        double delivery = businessRules.calculateDelivery(subtotal);
        return new OrderTO(items, subtotal, delivery, subtotal + delivery);
    }
}
