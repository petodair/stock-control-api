package io.github.stock_control_api.repository;

import io.github.stock_control_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findByEmail(String email);
}
