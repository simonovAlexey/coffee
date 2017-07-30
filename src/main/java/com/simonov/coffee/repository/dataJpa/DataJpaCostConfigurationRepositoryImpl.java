package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CostConfiguration;
import com.simonov.coffee.repository.CostConfigurationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Profile("spring-data-jpa")
@Repository
public class DataJpaCostConfigurationRepositoryImpl implements CostConfigurationRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<CostConfiguration> getAll(){
        List<CostConfiguration> list = new ArrayList<CostConfiguration>();
        list = this.em.createQuery("SELECT config FROM CostConfiguration config").getResultList();
        return Collections.unmodifiableList(list);
    }
}
