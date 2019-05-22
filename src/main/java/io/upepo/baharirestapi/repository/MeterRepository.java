package io.upepo.baharirestapi.repository;

import io.upepo.baharirestapi.model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<Meter, Long> {
}
