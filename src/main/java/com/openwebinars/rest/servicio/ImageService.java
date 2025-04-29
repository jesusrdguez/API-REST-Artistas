package com.openwebinars.rest.servicio;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openwebinars.rest.modelo.Image;
import com.openwebinars.rest.modelo.ImageRespository;
import com.openwebinars.rest.util.ImageUtil;

import jakarta.transaction.Transactional;

@Service
public class ImageService {
    
    @Autowired
    ImageRespository imageRespository;

    public MultipartFile uploadImage(MultipartFile file) throws IOException {
		imageRespository.save(Image.builder()
			.name(file.getOriginalFilename())
			.type(file.getContentType())
			.imageData(ImageUtil.compressImage(file.getBytes()))
			.build());

        return file;
    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<Image> dbImage = imageRespository.findByName(name);
        byte[] image = ImageUtil.compressImage(dbImage.get().getImageData());
        return image;
    } 
}
