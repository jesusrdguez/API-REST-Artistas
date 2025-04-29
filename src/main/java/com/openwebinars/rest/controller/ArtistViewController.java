package com.openwebinars.rest.controller;

import com.openwebinars.rest.modelo.ArtistRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class ArtistViewController {

    private final ArtistRepositorio artistRepositorio;

    @GetMapping("/add-artist")
    public String nuevoArtista() {
        return "add-artist";
    }
}