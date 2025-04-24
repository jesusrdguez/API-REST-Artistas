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

/**
 * Este componente extiende de DefaultErrorAttributes, lo que nos
 * permite customizar el JSON del error cuando ocurre una excepción.
 */
@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {

    /**
     * Sobreescribimos el método de la clase DefaultErrorAttributes para
     * crear nuestro propio error.
     * @param webRequest Representa la solicitud actual en la que ocurrió el error
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions includeStackTrace) {

        /**
         * Llamamos al método de la clase padre para hacer un mapa con todos los errores
         * que genera Spring Boot automáticamente.
         */
        Map<String, Object> allErrorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        /**
         * Creamos un nuevo mapa para los atributos personalizados.
         * Definimos un nuevo HashMap que contendrá los atributos que queremos
         * devolver al usuario en nuestro error personalizado.
         */
        Map<String, Object> errorAttributes = new HashMap<>();

        Map<String, Object> details = new HashMap<>();

        String path = (String) allErrorAttributes.get("path");
        details.put("path", path);

        errorAttributes.put("details", details);

        int statusCode = (int) allErrorAttributes.get("status"); // Obtemos el código de estado del map original
        /**
         * Añadimos a nuestro mensaje personalizado el estado que hemos extraído
         * anteriormete del mapa original. Además, le añadimos la fecha en la
         * que se ha lanzado la excepción.
         */
        errorAttributes.put("state", HttpStatus.valueOf(statusCode));
        errorAttributes.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        String message = "";

        Throwable throwable = getError(webRequest); // Obtenemos la excepción

        /**
         * Si la excepción es del tipo 'ResponseStatusException', entonces
         * comprobamos si la razón es nula para guardar un mensaje vacío, y si no
         * guardamos la razón de la excepción. En caso de no ser un tipo 'ResponseStatusException',
         * obtemeos la causa de la excepción. Esto nos es útil cuando la excepción se origina
         * en otra excepción, que es la causa real del error actual.
         */
        if (throwable instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) throwable;
            message = responseStatusException.getReason() == null ? "" : responseStatusException.getReason();
        } else {
            if (throwable.getCause() != null)
                message = throwable.getCause().getMessage() == null ? "" : throwable.getCause().getMessage();
        }

        details.put("message", message); // Añadimos el mensaje a la variable

        /**
         * Todo esto va a devolver un map<String, Object> con los atributos que son:
         *
         * state: código de estado Http convertido en un objeto HttpStatus
         * date: fecha y hora del momento en el ocurre la excepción
         * message: motivo por el cual se lanza la excepción
         */
        return errorAttributes;

    }
}
