package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.repository.CoffeeTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

@Profile("spring-data-jpa")
public interface DataJpaCoffeeTypeRepository extends JpaRepository<CoffeeType, Integer>, CoffeeTypeRepository {

    @Override
    @Query(value = "SELECT ct from CoffeeType ct where ct.disabled not like 'Y' order by ct.typeName asc")
    public List<CoffeeType> getAllEnabled();

    @Override
    @Query(value = "SELECT ct FROM CoffeeType ct WHERE ct.id IN :ids")
    public List<CoffeeType> getAllByIds(@Param("ids") Set<Integer> ids);


}
