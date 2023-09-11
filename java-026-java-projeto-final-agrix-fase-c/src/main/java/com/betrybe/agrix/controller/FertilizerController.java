package com.betrybe.agrix.controller;

import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
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
 * Fertilizer Controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Fertilizer createdFertilizer(@RequestBody Fertilizer fertilizer) {
    return fertilizerService.createdFertilizer(fertilizer);
  }

  @GetMapping
  @Secured({"ADMIN"})
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerService.getAllFertilizers();
  }

  /**
   * route get fertilizer by Id.
   */
  @GetMapping("{fertilizerId}")
  @Secured({"ADMIN"})
  public ResponseEntity<?> getFertilizerById(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> fertilizer = fertilizerService.getFertilizerById(fertilizerId);

    if (fertilizer.isEmpty()) {
      String responseEmpty = "Fertilizante não encontrado!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEmpty);
    }
    return ResponseEntity.ok(fertilizer);
  }
}
