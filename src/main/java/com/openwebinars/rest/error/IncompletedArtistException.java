package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompletedArtistException extends RuntimeException {

    public IncompletedArtistException() {
        super("No se han introducido los datos correctamente");
    }

}
