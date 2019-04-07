package de.garkolym.boilerplate.repository;

import de.garkolym.boilerplate.model.AuthUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUserModel, Long> {
    Optional<AuthUserModel> findByUsername(String username);
}
