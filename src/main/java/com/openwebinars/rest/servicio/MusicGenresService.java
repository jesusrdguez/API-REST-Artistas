package com.openwebinars.rest.servicio;

import com.openwebinars.rest.error.EmptyArtistsListException;
import com.openwebinars.rest.error.MusicGenreNotFound;
import com.openwebinars.rest.modelo.Artist;
import com.openwebinars.rest.modelo.MusicGenre;
import com.openwebinars.rest.modelo.MusicGenreRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicGenresService {

    @Autowired
    MusicGenreRepositorio musicGenreRepositorio;

    public List<MusicGenre> getAllMusicGenres() {
        List<MusicGenre> musicGenresList = musicGenreRepositorio.findAll();
        if (musicGenresList.isEmpty()) throw new EmptyArtistsListException();
        return musicGenresList;
    }

    public MusicGenre getMusicGenreById(Long genre_id) {
        return musicGenreRepositorio.findById(genre_id).
                orElseThrow(() -> new MusicGenreNotFound(genre_id));
    }

    public MusicGenre addMusicGenre(MusicGenre musicGenre) {
        return musicGenreRepositorio.save(musicGenre);
    }

    public MusicGenre editMusicGenre(MusicGenre musicGenre, Long genre_id) {
        return musicGenreRepositorio.findById(genre_id).map(g -> {
            g.setGenre_name(musicGenre.getGenre_name());
            return musicGenreRepositorio.save(g);
        }).orElseThrow(() -> {
            return new MusicGenreNotFound(genre_id);
        });
    }

    public MusicGenre deleteMusicGenre(Long genre_id) {
        return musicGenreRepositorio.findById(genre_id).map(g -> {
            MusicGenre deletedMusicGenre = g;
            musicGenreRepositorio.delete(g);
            return deletedMusicGenre;
        }).orElseThrow(() -> {
            return new MusicGenreNotFound(genre_id);
        });
    }

}
