//package com.openwebinars.rest.error;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@RestControllerAdvice
//public class ControllerAdvice extends ResponseEntityExceptionHandler {
//
//    /**
//     * Cuando se vaya a buscar un artista con un id que
//     * no exista, en vez de lanzar la excepción, se ejecutará
//     * este método que devuelve un error personalizado.
//     *
//     * @param ex excepción cuando no se encuentra un artista
//     * @return
//     */
//    @ExceptionHandler(MusicGenreNotFound.class)
//    public ResponseEntity<NotFoundApiError> handleMusicGenreNotFoundException(MusicGenreNotFound ex) {
//        NotFoundApiError notFoundApiError = new NotFoundApiError(ex.getMessage());
//        return ResponseEntity.status(notFoundApiError.getState()).body(notFoundApiError);
//    }
//
//    /**
//     * Controlar todas las excepciones.
//     *
//     * @param ex
//     * @param body
//     * @param headers
//     * @param status
//     * @param request
//     * @return
//     */
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        return super.handleExceptionInternal(ex, body, headers, status, request);
//        ApiError apiError = new ApiError(status, ex.getMessage());
//        return ResponseEntity.status(status).headers(headers).body(apiError);
//    }
//
//
//}
