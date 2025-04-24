package com.openwebinars.rest.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// hacemos uso de lombok para crear los getters,
// setters, contructores, etc., sin tener que
// escribir todo el código
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "artists") // RESALTAMOS Y ACLARAMOS EL NOMBRE DE LA TABLA
public class Artist {

	@Id
	// Esta línea indica que el valor del campo artist_id
	// se genera automáticamente, y no es necesario que se
	// encuentre en el cuerpo de la petición
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artist_id;

	private String name;

	private String nationality;

	// Esta es una de las anotaciones más habituales a nive de JPA, y
	// se encarga de generar una relación de muchos a uno (n:1)
	@ManyToOne
	// Esta anotación JPA es utilizada para referenciar a la columna
	// que es la foreign key en la tabla, y así definir la relación
	@JoinColumn(name = "genre_id")
	// Al crear un campo de tipo 'MusicGenre' estamos referenciando
	// a la tabla 'music_genre'
	private MusicGenre musicGenre;

}
