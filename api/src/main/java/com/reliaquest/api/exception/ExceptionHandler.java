package com.reliaquest.api.exception;

import com.fasterxml.jackson.databind.JsonNode;
import java.nio.charset.StandardCharsets;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

/**
 * Exception handler class to handle Server API non 200 server codes
 */
@Component
public class ExceptionHandler {
    public RestClientException exceptionByStatus(ResponseEntity<JsonNode> response) {
        byte[] bodyBytes = (response.hasBody()) ? response.getBody().toString().getBytes() : null;
        if (response.getStatusCode().is4xxClientError()) {
            return HttpClientErrorException.create(
                    response.getStatusCode(), "Client Error", response.getHeaders(), bodyBytes, StandardCharsets.UTF_8);
        }
        if (response.getStatusCode().is5xxServerError()) {
            return HttpServerErrorException.create(
                    response.getStatusCode(),
                    "Server Side Error",
                    response.getHeaders(),
                    bodyBytes,
                    StandardCharsets.UTF_8);
        }
        return new RestClientException("");
    }
}
