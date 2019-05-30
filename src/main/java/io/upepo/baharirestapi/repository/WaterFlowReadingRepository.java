package io.upepo.baharirestapi.repository;

import io.upepo.baharirestapi.model.WaterFlowReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterFlowReadingRepository extends JpaRepository<WaterFlowReading,Long> {
}
