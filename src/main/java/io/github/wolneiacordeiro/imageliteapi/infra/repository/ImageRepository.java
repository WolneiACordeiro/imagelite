package io.github.wolneiacordeiro.imageliteapi.infra.repository;

import io.github.wolneiacordeiro.imageliteapi.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
