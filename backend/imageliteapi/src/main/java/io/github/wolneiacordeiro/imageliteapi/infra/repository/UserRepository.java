package io.github.wolneiacordeiro.imageliteapi.infra.repository;

import io.github.wolneiacordeiro.imageliteapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
