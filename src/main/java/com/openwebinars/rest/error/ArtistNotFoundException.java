package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(Long id) {
        super("No se puede encontrar el artista con el ID: " + id);
    }

}
