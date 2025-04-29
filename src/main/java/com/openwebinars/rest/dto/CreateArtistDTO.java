package com.openwebinars.rest.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CreateArtistDTO {

    private String name;
    private Long genre_id;
    private String nationality;

}
