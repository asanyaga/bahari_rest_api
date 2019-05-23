package io.upepo.baharirestapi.repository;


import io.upepo.baharirestapi.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConnectionRepository extends JpaRepository<Connection,Long> {
}
