package com.openwebinars.rest.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions includeStackTrace) {

        Map<String, Object> allErrorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String, Object> errorAttributes = new HashMap<>();

        Map<String, Object> details = new HashMap<>();

        String path = (String) allErrorAttributes.get("path");
        details.put("path", path);

        errorAttributes.put("details", details);

        int statusCode = (int) allErrorAttributes.get("status");

        errorAttributes.put("state", HttpStatus.valueOf(statusCode));
        errorAttributes.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        String message = "";

        Throwable throwable = getError(webRequest); // Obtenemos la excepción

        if (throwable instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) throwable;
            message = responseStatusException.getReason() == null ? "" : responseStatusException.getReason();
        } else {
            if (throwable.getCause() != null)
                message = throwable.getCause().getMessage() == null ? "" : throwable.getCause().getMessage();
        }

        details.put("message", message);

        return errorAttributes;
    }
}
