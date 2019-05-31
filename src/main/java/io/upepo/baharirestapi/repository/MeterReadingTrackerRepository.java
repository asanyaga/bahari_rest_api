package io.upepo.baharirestapi.repository;

import io.upepo.baharirestapi.model.MeterReadingTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MeterReadingTrackerRepository extends JpaRepository<MeterReadingTracker, Long> {

    Optional<MeterReadingTracker> findByMeterId(Long meterId);

}
