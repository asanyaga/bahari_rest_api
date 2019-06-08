package io.upepo.baharirestapi.repository;

import io.upepo.baharirestapi.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
