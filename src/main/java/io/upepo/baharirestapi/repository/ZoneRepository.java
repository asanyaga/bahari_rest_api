package io.upepo.baharirestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.upepo.baharirestapi.model.Zone;

public interface ZoneRepository  extends JpaRepository<Zone, Long> {
}
