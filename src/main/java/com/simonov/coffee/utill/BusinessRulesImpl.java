package com.simonov.coffee.utill;

import com.simonov.coffee.model.CostConfiguration;
import com.simonov.coffee.repository.CostConfigurationRepository;
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

    private int eachNCupFree;
    private int deliveryCost;
    private int freeDelivery;
    private CostConfigurationRepository costConfigurationRepository;

    @Autowired
    public BusinessRulesImpl(CostConfigurationRepository costConfigurationRepository,
                             @Value("${br.freeDelivery:-1}") Integer freeDeliveryP,
                             @Value("${br.deliveryCost:-1}") Integer deliveryCostP,
                             @Value("${br.eachNCupFree:-1}") Integer eachNCupFreeP
    ) {
        this.costConfigurationRepository = costConfigurationRepository;
        if (freeDeliveryP == -1 || deliveryCostP == -1 || eachNCupFreeP == -1) {
            LOG.info("Configure with DB");
            init(costConfigurationRepository);
        } else {
            LOG.info("Configure with properties");
            this.eachNCupFree = eachNCupFreeP;
            this.deliveryCost = deliveryCostP;
            this.freeDelivery = freeDeliveryP;
        }
    }

    public BusinessRulesImpl() {
        LOG.debug("Configure with default no args constructor");
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
    public double calculateSubTotalCost(@NonNull Integer quantity, double price) {
        return (quantity - (quantity / eachNCupFree)) * price;
    }


    @Override
    public double calculateDelivery(double subtotal) {
        return subtotal > freeDelivery ? 0 : deliveryCost;
    }

    @Override
    public int getNFreeCup() {
        return eachNCupFree;
    }
}

