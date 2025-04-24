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
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import com.openwebinars.rest.modelo.Artist;
import com.openwebinars.rest.modelo.ArtistRepositorio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// RestController => todos los métodos devuelven directamente
// el cuerpo de la respuesta (por ejemplo, JSON)
@RestController
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistRepositorio artistRepositorio;
    private final MusicGenreRepositorio musicGenreRepositorio;
    private final ArtistDTOConverter artistDTOConverter;
    private final GestorArtistasServicio gestorArtistasServicio;
    private final ModelMapper modelMapper;
    private final ApplicationArguments applicationArguments;

    /**
     * Obtenemos todos los artistas
     *
     * @return
     */
    @GetMapping("/artists")
    // Marcamos con la integorración porque va a mandar un
    // ResponseEntity de algo pero no sabemos de qué
    public ResponseEntity<?> obtenerTodos() {

        List<Artist> result = gestorArtistasServicio.findAllArtists();

//        if (result.isEmpty()) {
//              EmptyArtistsListException emptyArtistsListException = new EmptyArtistsListException();
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyArtistsListException);
//        } else {
//            // si hay artistas que mandar le mandamos un 'ok'
//            // al cliente, y en el cuerpo los artistas que hay (result)
//            return ResponseEntity.ok(result);
//
        /**
         * Nos va a devolver solo los atributos que se encuentran
         * en nuestro ArtistDTO (name, music_genre)
         */
        List<ArtistDTO> dtoList = // lista de tipo ArtistDTO
                result.stream()
                        .map(artistDTOConverter::convertToDto)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
//        }
    }

    /**
     * Obtenemos un artista en base a su ID
     *
     * @param artist_id
     * @return Null si no encuentra el artista
     */
    @GetMapping("/artists/{artist_id}")
    public ResponseEntity<?> obtenerUno(@PathVariable Long artist_id) {
        // Buscamos en el repositorio un artista que contenga
        // el id a buscar, sino devuelve null
//        Artist result = artistRepositorio.findById(artist_id).orElse(null); // devuelve un optional
//
//        if (result == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(result);
//        }

        Artist artist = gestorArtistasServicio.findArtistById(artist_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(artist);
    }

    /**
     * Insertamos un nuevo artista
     * Recibe como paramatro un objeto de tipo Artist.
     * Para coger este objeto Artist revisa el cuerpo
     * de la petición
     *
     * @param nuevo
     * @return artista insertado
     */
    @PostMapping("/artists")
    // public ResponseEntity<Artist> nuevoArtist(@RequestBody Artist nuevo) {
    public ResponseEntity<Artist> nuevoArtist(@RequestBody CreateArtistDTO nuevo) {
//        Artist saved = artistRepositorio.save(nuevo);
//        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        // El DTO es convertido a entidad en el controlador
        Artist artist = convertToEntity(nuevo); // Le pasamos el DTO al método para convertirlo a entidad
        // Llamamos al servicio para utilizar el método que guarda
        // un artista, y nos devuelve el artista guardado. Este artista
        // guardado se guarda en una variable para devolverlo
        Artist artistCreated = gestorArtistasServicio.createArtist(artist);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistCreated);
    }

    /**
     * @param editar
     * @param artist_id
     * @return
     */
    @PutMapping("/artists/{artist_id}")
    //public ResponseEntity<?> editarArtist(@RequestBody Artist editar, @PathVariable Long artist_id) {
    public Artist editarArtist(@RequestBody CreateArtistDTO editar, @PathVariable Long artist_id) {

//        return artistRepositorio.findById(artist_id).map(a -> { // Buscamos el artista dentro del array por Id
//            a.setName(editar.getName()); // Cambiamos el nombre del artista
//            return ResponseEntity.ok(artistRepositorio.save(a)); //
//        }).orElseGet(() -> { // En caso de no encontrar el artista
//            return ResponseEntity.notFound().build(); // se devuelve un 404 not found indicando que no está
//        });

        // Esta función debe de recibir un DTO, llamar a la función
        // para convertirlo en Entidad, y pasárselo al servicio.
        // En el servicio se debe de editar el artista haciendo
        // uso del repositorio

        Artist artist = convertToEntity(editar); // Transformamos el DTO a entidad y lo guardamos
        return gestorArtistasServicio.editArtist(artist, artist_id);

    }

    /**
     * Esta función se encarga de transformar un DTO a una entidad.
     * Específicamente un CreateArtistDTO a una entidad Artist.
     *
     * @param dto
     * @return
     */
    private Artist convertToEntity(CreateArtistDTO dto) {
        Artist artist = new Artist(); // Creamos una nueva entidad
        // Cogemos el nombre del DTO, y se lo asignamos a la nueva entidad
        artist.setName(dto.getName());

        /**
         * Hacemos uso del repositorio MusicGenreRepository, para buscar
         * el género musical correspondiente al id, y así recogerlo para
         * posteriormente asignárselo al artista.
         */
        MusicGenre musicGenre = musicGenreRepositorio.findById(dto.getGenre_id())
                .orElseThrow(IncompletedArtistException::new);
        artist.setMusicGenre(musicGenre); // asignamos el género musical

        return artist; // devolvemos el artista
    }

    /**
     * Borra un artista del catálogo en base a su id
     *
     * @param artist_id
     * @return
     */
    @DeleteMapping("/artists/{artist_id}")
    public Artist borrarArtist(@PathVariable Long artist_id) {
//        if (artistRepositorio.existsById(artist_id)) {
//            // Se hace una búsqueda del artista en el repositorio,
//            // y una vez lo ha encontrado se utiliza el método 'get()',
//            // para extraer la instancia real del artista
//            Artist artista = artistRepositorio.findById(artist_id).get();
//            artistRepositorio.deleteById(artist_id);
//            return artista;
//        } else {
//            return null;
//        }

        // artistRepositorio.deleteById(artist_id);

        return gestorArtistasServicio.deleteArtist(artist_id);

        // Este nos devolvería un JSON vacío con el código
        // correcto de que se ha eliminado (204)
        // return ResponseEntity.noContent().build();

    }

}
