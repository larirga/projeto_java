package com.betrybe.agrix.service;

import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Farm Service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm createdFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarm() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }
}
