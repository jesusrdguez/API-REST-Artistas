package com.openwebinars.rest.controller;

import java.io.IOException;

import javax.print.attribute.standard.Media;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.openwebinars.rest.modelo.Image;
import com.openwebinars.rest.modelo.ImageRespository;
import com.openwebinars.rest.servicio.ImageService;
import com.openwebinars.rest.upload.StorageService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class FicherosController {

    private final ImageRespository imageRespository;
	private final ImageService imageService;
	
	private static final Logger logger = LoggerFactory.getLogger(FicherosController.class);

	
	private final StorageService storageService;

	// @GetMapping(value="/files/{name}")
	// public ResponseEntity<?> getImageByName(@PathVariable("name") String name) {
	// 	byte[] image = imageService.getImage(name);

	// 	return ResponseEntity.status(200)
	// 			.contentType(MediaType.valueOf("image/png"))
	// 			.body(image);
	// }

	@PostMapping(value="/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> subirImagen(@RequestPart("file") MultipartFile nuevaImagen) throws IOException {
		MultipartFile response = imageService.uploadImage(nuevaImagen);		

		return ResponseEntity.status(201).body(null);
	}
	
	
	
	@GetMapping(value="/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> serveFile(@PathVariable String filename, HttpServletRequest request) {
		
		Resource file = storageService.loadAsResource(filename);
		
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(file);
	}

}