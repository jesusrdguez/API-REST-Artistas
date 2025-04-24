package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmptyArtistsListException extends RuntimeException {

    public EmptyArtistsListException() {
        super("No se ha encontrado ningún artista");
    }

}
