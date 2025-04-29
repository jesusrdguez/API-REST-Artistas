package com.openwebinars.rest.modelo;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artist_id;

	private String name;

	private String nationality;

	private List<String> images;

	@ManyToOne
	@JoinColumn(name = "genre_id")
	private MusicGenre musicGenre;

}
