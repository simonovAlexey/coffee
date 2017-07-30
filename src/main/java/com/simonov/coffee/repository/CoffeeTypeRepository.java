package com.simonov.coffee.repository;

import com.simonov.coffee.model.CoffeeType;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Set;

public interface CoffeeTypeRepository {


    CoffeeType findById(int id) throws DataAccessException;

    List<CoffeeType> getAllEnabled() throws DataAccessException;

    List<CoffeeType> getAllByIds(Set<Integer> ids);
}
