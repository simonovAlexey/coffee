package com.simonov.coffee.utill;

import com.simonov.coffee.model.CostConfiguration;
import com.simonov.coffee.repository.CostConfigurationRepository;
import com.simonov.coffee.to.CoffeeOrderItemTo;
import com.simonov.coffee.utill.exception.NotFoundException;
import lombok.Data;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
//@PropertySource("classpath:/application.properties")
public class BusinessRulesImpl implements BusinessRules {
    private static final Logger LOG = LoggerFactory.getLogger(BusinessRulesImpl.class);

    private int eachNCupFree; // each N-cup of same coffee-sort is free
    private int deliveryCost;
    private int freeDelivery; // if order's cost greater than this value, then  free  delivery

    private CostConfigurationRepository costConfigurationRepository;

/*    @Value("${br.freeDelivery}")
    public int freeDeliveryProp;

    @Value("${br.deliveryCost}")
    private int deliveryCostProp;

    @Value("${br.eachNCupFree}")
    private int eachNCupFreeProp;
    TODO Autowired properties in constructor*/

    @Autowired
    public BusinessRulesImpl(CostConfigurationRepository costConfigurationRepository,
                             @Value("${some.prop}")String sp) {
        this.costConfigurationRepository = costConfigurationRepository;
//        this.deliveryCostProp=sp;
        init(costConfigurationRepository);
    }

    private void init(CostConfigurationRepository costConfigurationRepository) throws NotFoundException {
        List<CostConfiguration> list = costConfigurationRepository.getAll();
        for (CostConfiguration config : list) {
            int value;
            try {
                value = Integer.parseInt(config.getValue());
            } catch (NumberFormatException e) {
                LOG.debug("Error parsing CostConfiguration ", e);
                throw new NotFoundException("Error parsing CostConfiguration");
            }

            if (config.getId().equalsIgnoreCase("m")) deliveryCost = value;
            if (config.getId().equalsIgnoreCase("x")) freeDelivery = value;
            if (config.getId().equalsIgnoreCase("n")) eachNCupFree = value;

        }
    }

        /*TODO Configuration via Java
        try {
            Object dddddd = Class.forName("dddddd").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }*/



    @Override
    public double calculateSubTotalCost(@NonNull CoffeeOrderItemTo item) {
        return calculateSubTotalCost(item.getQuantity(), item.getPrice());

    }
    @Override
    public double calculateSubTotalCost(@NonNull Integer quantity, double price){
        return (quantity - (quantity / eachNCupFree)) * price;
    }

    @Override
    public double calculateOrderSubTotal(@NonNull List<CoffeeOrderItemTo> items) {
        double cost = 0;
        for (CoffeeOrderItemTo orderItem : items) {
            cost += calculateSubTotalCost(orderItem);
        }
        return cost;
    }

    @Override
    public double calculateDelivery(@NonNull double subtotal) {
        return subtotal > freeDelivery ? 0 : deliveryCost;
    }



}

