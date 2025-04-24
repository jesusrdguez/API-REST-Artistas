package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MusicGenreNotFound extends RuntimeException {

    public MusicGenreNotFound(Long genre_id) {
        super("No se ha encontrado ningún género musical con el id " + genre_id);
    }

}
