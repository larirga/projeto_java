package com.betrybe.agrix.service;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop Service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;

  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public Crop createdCrop(Crop crop, Farm farm) {
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrop() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getCropByIdFarm(Farm farm) {
    return cropRepository.findByFarm(farm);
  }

  public List<Crop> getSearchByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }
}

