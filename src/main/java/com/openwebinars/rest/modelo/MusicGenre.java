package com.openwebinars.rest.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "music_genres")
public class MusicGenre {

    @Id // Indicamos la clave primaria de la entidad
    // El campo id se generará automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genre_id;

    private String genre_name;
}
