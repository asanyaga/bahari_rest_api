package io.upepo.baharirestapi.repository;

import io.upepo.baharirestapi.model.WaterFlowReading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaterFlowReadingRepository extends JpaRepository<WaterFlowReading,Long> {
    Page<WaterFlowReading> findByMeterIdOrderByReadingDateDesc(Long meterId, Pageable pageRequest);
}
