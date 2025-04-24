//package com.openwebinars.rest.error;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.http.HttpStatus;
//
//import java.time.LocalDateTime;
//
//@Getter @Setter
//public class NotFoundApiError extends ApiError {
//
//    /**
//     * Constructor que establece el estado de la excepción
//     * en Not Found.
//     * @param m Mensaje de la excepción
//     */
//    public NotFoundApiError(String m) {
//        super.setState(HttpStatus.NOT_FOUND);
//        super.setMessage(m);
//    }
//
//}
