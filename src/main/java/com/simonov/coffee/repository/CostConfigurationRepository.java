package com.simonov.coffee.repository;

import com.simonov.coffee.model.CostConfiguration;

import java.util.List;

public interface CostConfigurationRepository {

    List<CostConfiguration> getAll();
}
