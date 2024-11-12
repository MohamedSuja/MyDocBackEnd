package com.suja.mydoc.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {


        // Set the status code to unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Set content type to JSON
        response.setContentType("application/json");

        // Create a response body with the desired format
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String path = request.getRequestURI();
        String errorMessage = authException.getMessage() != null ? authException.getMessage() : "";

        // JSON error response
        String errorResponse = String.format(
                "{\"timestamp\": \"%s\", \"status\": 401, \"error\": \"Unauthorized\", \"message\": \"%s\", \"path\": \"%s\"}",
                timestamp, errorMessage, path
        );

        // Write the error response to the response body
        response.getWriter().write(errorResponse);
    }
}
