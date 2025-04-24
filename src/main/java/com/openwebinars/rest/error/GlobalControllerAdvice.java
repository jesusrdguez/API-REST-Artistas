//package com.openwebinars.rest.error;
//
//import org.apache.coyote.Response;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MissingPathVariableException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//
///**
// * Da un tratamiento más amplio de las excepciones. Con
// * esta anotación podemos decidir los paquetes, clases y
// * controladores de los cuales queremos manejar excepciones.
// */
//@RestControllerAdvice
//public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
//
//    /**
//     * Cuando se vaya a buscar un artista con un id que
//     * no exista, en vez de lanzar la excepción, se ejecutará
//     * este método que devuelve un error personalizado.
//     *
//     * @param ex excepción cuando no se encuentra un artista
//     * @return
//     */
//    @ExceptionHandler(ArtistNotFoundException.class)
//    public ResponseEntity<NotFoundApiError> handleArtistNotFound(ArtistNotFoundException ex) {
//        NotFoundApiError notFoundApiError = new NotFoundApiError(ex.getMessage());
//        return ResponseEntity.status(notFoundApiError.getState()).body(notFoundApiError);
//    }
//
//    /**
//     * Controlar todas las excepciones.
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
////    @ExceptionHandler(HttpMessageNotReadableException.class)
////    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
////        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
////        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
////    }
//
//}
