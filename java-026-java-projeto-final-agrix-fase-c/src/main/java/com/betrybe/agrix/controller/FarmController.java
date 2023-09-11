package com.betrybe.agrix.controller;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm Controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Farm createdFarm(@RequestBody Farm farm) {
    return farmService.createdFarm(farm);
  }

  @GetMapping
  @Secured({"USER", "MANAGER", "ADMIN"})
  public List<Farm> getAllFarm() {
    return farmService.getAllFarm();
  }

  /**
   * Route Get By id.
   */
  @GetMapping("/{id}")
  @Secured({"USER", "MANAGER", "ADMIN"})
  public ResponseEntity<?> getFarmById(@PathVariable Long id) {
    Optional<Farm> farm = farmService.getFarmById(id);

    if (farm.isEmpty()) {
      String responseEmpty = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEmpty);
    }
    return ResponseEntity.ok(farm);
  }

  @Autowired
  private CropService cropService;

  /**
   * Route Crop Create By id.
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createdCrop(
      @PathVariable Long farmId,
      @RequestBody Crop crop) {
    Optional<Farm> farm = farmService.getFarmById(farmId);
    if (farm.isEmpty()) {
      String responseEmpty = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEmpty);
    }
    Crop newCropResponse = cropService.createdCrop(crop, farm.get());
    return ResponseEntity.status(HttpStatus.CREATED).body(newCropResponse);
  }

  /**
   * Route Crop Get By id.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getCropsByIdFarm(@PathVariable Long farmId) {
    Optional<Farm> farm = farmService.getFarmById(farmId);
    if (farm.isEmpty()) {
      String responseEmpty = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEmpty);
    }
    List<Crop> allCrops = cropService.getCropByIdFarm(farm.get());
    return ResponseEntity.ok(allCrops);
  }
}