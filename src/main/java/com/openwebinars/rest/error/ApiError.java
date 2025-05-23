//package com.openwebinars.rest.error;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.*;
//import org.springframework.http.HttpStatus;
//
//import java.time.LocalDateTime;
//
//@Getter @Setter @RequiredArgsConstructor @NoArgsConstructor
//public class ApiError {
//
//    @NonNull
//    private HttpStatus state;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy hh:mm:ss")
//    private LocalDateTime date = LocalDateTime.now();
//    @NonNull
//    private String message;
//
//}
