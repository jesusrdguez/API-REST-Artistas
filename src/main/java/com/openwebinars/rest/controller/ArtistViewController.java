package com.openwebinars.rest.controller;

import com.openwebinars.rest.modelo.ArtistRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Este controlador tendrá una serie de vistas
// para poder trabajar con los artists
@Controller
@RequiredArgsConstructor // contructor con un parámetro por cada atributo
public class ArtistViewController {

    // @Autowired => es otra forma de incializar atributos
    private final ArtistRepositorio artistRepositorio;

    // otra forma de incicializar un repositorio
//    public ArtistController(ArtistRepositorio artistRepositorio) {
//        this.artistRepositorio = artistRepositorio;
//    }

    @GetMapping("/add-artist")
    public String nuevoArtista() {
        return "add-artist";
    }
}
