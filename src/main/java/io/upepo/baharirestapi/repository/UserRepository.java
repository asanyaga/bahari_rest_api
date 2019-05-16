package io.upepo.baharirestapi.repository;
import io.upepo.baharirestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

List<User> findByusername(String username);

Optional<User> findByUsername(String username);

List<User> findByemail(String email);
}
