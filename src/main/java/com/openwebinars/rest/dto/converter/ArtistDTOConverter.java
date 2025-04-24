package com.openwebinars.rest.dto.converter;

import com.openwebinars.rest.dto.ArtistDTO;
import com.openwebinars.rest.modelo.Artist;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component // ¿Qué significa que sea un componente?
@RequiredArgsConstructor
public class ArtistDTOConverter {

    private final ModelMapper modelMapper;

    // Función que recibe un Artist y lo convierte
    // a un objeto ArtistDTO.
    public ArtistDTO convertToDto(Artist artist) {

        // Este método necesita de un objeto origen (artist)
        // y el tipo de clase de destino
        return modelMapper.map(artist, ArtistDTO.class);
    }

}
