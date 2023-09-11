package com.betrybe.agrix.controller;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop Controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * route get all crop.
   */
  @GetMapping
  @Secured({"MANAGER", "ADMIN"})
  public List<Crop> getAllCrop() {
    return cropService.getAllCrop();
  }

  /**
   * route get crop by id.
   */
  @GetMapping("/{id}")
  @Secured({"MANAGER", "ADMIN"})
  public ResponseEntity<?> getCropById(@PathVariable Long id) {
    Optional<Crop> crop = cropService.getCropById(id);

    if (crop.isEmpty()) {
      String responseEmpty = "Plantação não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEmpty);
    }
    return ResponseEntity.ok(crop);
  }

  /**
   * route get crop search by Harvest Date.
   */
  @GetMapping("/search")
  public ResponseEntity<List<Crop>> getSearchByHarvestDate(
      @RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate start,
      @RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate end
  ) {
    List<Crop> allCrops = cropService.getSearchByHarvestDate(start, end);
    return ResponseEntity.ok(allCrops);
  }
}