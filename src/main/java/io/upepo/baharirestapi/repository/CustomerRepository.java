package io.upepo.baharirestapi.repository;
import io.upepo.baharirestapi.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
