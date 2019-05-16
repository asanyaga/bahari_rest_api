package io.upepo.baharirestapi.repository;

import io.upepo.baharirestapi.model.Role;
import io.upepo.baharirestapi.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(RoleName roleName);
}
