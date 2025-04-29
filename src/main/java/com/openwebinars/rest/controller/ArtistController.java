package com.openwebinars.rest.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.openwebinars.rest.dto.ArtistDTO;
import com.openwebinars.rest.dto.CreateArtistDTO;
import com.openwebinars.rest.dto.converter.ArtistDTOConverter;
import com.openwebinars.rest.error.ArtistNotFoundException;
import com.openwebinars.rest.error.IncompletedArtistException;
import com.openwebinars.rest.modelo.MusicGenre;
import com.openwebinars.rest.modelo.MusicGenreRepositorio;
import com.openwebinars.rest.servicio.GestorArtistasServicio;
import com.openwebinars.rest.upload.StorageService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import com.openwebinars.rest.modelo.Artist;
import com.openwebinars.rest.modelo.ArtistRepositorio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.print.attribute.standard.Media;


@RestController
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistRepositorio artistRepositorio;
    private final MusicGenreRepositorio musicGenreRepositorio;
    private final ArtistDTOConverter artistDTOConverter;
    private final GestorArtistasServicio gestorArtistasServicio;
    private final ModelMapper modelMapper;
    private final ApplicationArguments applicationArguments;
    private final StorageService storageService;


    @GetMapping("/artists")
    public ResponseEntity<?> obtenerTodos() {

        List<Artist> result = gestorArtistasServicio.findAllArtists();

        List<ArtistDTO> dtoList =
                result.stream()
                        .map(artistDTOConverter::convertToDto)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/artists/{artist_id}")
    public ResponseEntity<?> obtenerUno(@PathVariable Long artist_id) {
        Artist artist = gestorArtistasServicio.findArtistById(artist_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(artist);
    }


    @PostMapping(value = "/artists", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Artist> nuevoArtist(@RequestPart("nuevo") CreateArtistDTO nuevo,
            @RequestPart("file") MultipartFile[] files) {

        List<String> urlImages = new ArrayList<>();

        if (files.length > 0) {
            for (MultipartFile file : files) {
                String image = storageService.store(file);
                String urlImage = MvcUriComponentsBuilder
                        .fromMethodName(FicherosController.class, "serveFile", image, null)
                        .build().toUriString();

                urlImages.add(urlImage);
            }
        } else
            urlImages = null;

        Artist artist = convertToEntity(nuevo);
        artist.setImages(urlImages);
        Artist artistCreated = gestorArtistasServicio.createArtist(artist);

        return ResponseEntity.status(HttpStatus.CREATED).body(artistCreated);
    }


    @PutMapping("/artists/{artist_id}")
    public Artist editarArtist(@RequestBody CreateArtistDTO editar, @PathVariable Long artist_id) {
        Artist artist = convertToEntity(editar);
        return gestorArtistasServicio.editArtist(artist, artist_id);
    }


    private Artist convertToEntity(CreateArtistDTO dto) {
        Artist artist = new Artist();
        artist.setName(dto.getName());
        artist.setNationality(dto.getNationality());

        MusicGenre musicGenre = musicGenreRepositorio.findById(dto.getGenre_id())
                .orElseThrow(IncompletedArtistException::new);
        artist.setMusicGenre(musicGenre);

        return artist;
    }


    @DeleteMapping("/artists/{artist_id}")
    public Artist borrarArtist(@PathVariable Long artist_id) {
        return gestorArtistasServicio.deleteArtist(artist_id);
    }

}
