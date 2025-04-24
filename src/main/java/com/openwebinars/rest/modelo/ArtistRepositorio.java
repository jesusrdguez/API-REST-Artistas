package com.openwebinars.rest.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Los repositorios se nos es facilitado por Spring Data.
 * Y nos ayudan con las tareas de mantenimiento de datos,
 * y poder nosotros librarnos de esa tarea.
 *
 * Además, nos facilita una serie de métodos que no tenemos
 * crear nosotros, sino que el propio repositorio ya
 * lo tiene creado. Como save(), edit(), delteById(),
 * findById(), findAll(), etc.
 */
public interface ArtistRepositorio extends JpaRepository<Artist, Long> {

}
