package com.openwebinars.rest.modelo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRespository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
