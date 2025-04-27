package com.openwebinars.rest.dto.converter;

import com.openwebinars.rest.dto.ArtistDTO;
import com.openwebinars.rest.modelo.Artist;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistDTOConverter {

    private final ModelMapper modelMapper;

    
    public ArtistDTO convertToDto(Artist artist) {
        return modelMapper.map(artist, ArtistDTO.class);
    }

}
