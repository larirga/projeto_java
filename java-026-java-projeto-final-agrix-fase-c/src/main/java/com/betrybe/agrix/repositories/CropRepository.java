package com.betrybe.agrix.repositories;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Crop Repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByFarm(Farm farm);

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
