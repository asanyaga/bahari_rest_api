package io.upepo.baharirestapi.repository;

import io.upepo.baharirestapi.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
