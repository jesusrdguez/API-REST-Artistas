package com.openwebinars.rest.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Este DTO nos puede servir para recoger los datos
 * de un formulario que nos envía desde una aplicación,
 * hecha con Angular por ejemplo, para la creación de un
 * nuevo producto.
 */
@Getter @Setter
public class CreateArtistDTO {

    private String name;
    private Long genre_id;

}
