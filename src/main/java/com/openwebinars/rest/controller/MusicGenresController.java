package com.openwebinars.rest.controller;

import com.openwebinars.rest.modelo.MusicGenre;
import com.openwebinars.rest.servicio.MusicGenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MusicGenresController {

    private final MusicGenresService musicGenresService;

    @GetMapping("/musicGenres")
    public ResponseEntity<?> getAllMusicGenres() {
        List<MusicGenre> result = musicGenresService.getAllMusicGenres();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/musicGenres/{genre_id}")
    public MusicGenre getMusicGenreById(@PathVariable Long genre_id) {
        return musicGenresService.getMusicGenreById(genre_id);
    }

    @PostMapping("/musicGenres")
    public ResponseEntity<?> addMusicGenre(@RequestBody MusicGenre musicGenre) {
        MusicGenre newMusicGenre = musicGenresService.addMusicGenre(musicGenre);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMusicGenre);
    }

    @PutMapping("/musicGenres/{genre_id}")
    public ResponseEntity<?> editMusicGenre(@RequestBody MusicGenre musicGenre, @PathVariable Long genre_id) {
        MusicGenre editedMusicGenre = musicGenresService.editMusicGenre(musicGenre, genre_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(editedMusicGenre);
    }

    @DeleteMapping("/musicGenres/{genre_id}")
    public ResponseEntity<?> deleteMusicGenre(@PathVariable Long genre_id) {
        MusicGenre deletedMusicGenre = musicGenresService.deleteMusicGenre(genre_id);
        return ResponseEntity.ok(deletedMusicGenre);
    }

}
