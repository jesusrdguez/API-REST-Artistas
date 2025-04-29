package com.openwebinars.rest.servicio;

import com.openwebinars.rest.error.ArtistNotFoundException;
import com.openwebinars.rest.error.EmptyArtistsListException;
import com.openwebinars.rest.error.IncompletedArtistException;
import com.openwebinars.rest.error.MusicGenreNotFound;
import com.openwebinars.rest.modelo.Artist;
import com.openwebinars.rest.modelo.ArtistRepositorio;
import com.openwebinars.rest.modelo.MusicGenre;
import com.openwebinars.rest.modelo.MusicGenreRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class GestorArtistasServicio {

    @Autowired
    ArtistRepositorio artistRepositorio;
    @Autowired
    MusicGenreRepositorio musicGenreRepositorio;


    public List<Artist> findAllArtists() {
        List<Artist> artistsList = artistRepositorio.findAll();

        if (artistsList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay artistas registrados");

        return artistsList;
    }

    public Artist findArtistById(Long artist_id) {
        try {
            return artistRepositorio.findById(artist_id)
                    .orElseThrow(() -> new ArtistNotFoundException(artist_id));
        } catch (ArtistNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }


    public Artist createArtist(Artist artist) {
        if (artist.getName().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else
            return artistRepositorio.save(artist);
    }


    public Artist editArtist(Artist artist, Long artist_id) {
        try {
            return artistRepositorio.findById(artist_id).map(a -> {
                a.setName(artist.getName());
                try {
                    MusicGenre musicGenre = musicGenreRepositorio.findById(artist.getMusicGenre().getGenre_id())
                            .orElseThrow(() -> new IncompletedArtistException());
                    a.setMusicGenre(musicGenre);
                } catch (MusicGenreNotFound ex) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
                }
                return artistRepositorio.save(a);
            }).orElseThrow(() -> new ArtistNotFoundException(artist_id));
        } catch (ArtistNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    public Artist deleteArtist(Long artist_id) {
        try {
            return artistRepositorio.findById(artist_id).map(a -> {
                Artist artist = a;
                artistRepositorio.delete(a);
                return artist;
            }).orElseThrow(() -> new ArtistNotFoundException(artist_id));
        } catch (ArtistNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
