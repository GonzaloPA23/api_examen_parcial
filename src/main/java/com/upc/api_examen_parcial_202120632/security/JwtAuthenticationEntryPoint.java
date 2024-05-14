package com.upc.api_examen_parcial_202120632.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest rgpaRequest, HttpServletResponse rgpaResponse, AuthenticationException rgpaAuthException) throws IOException {
        rgpaResponse.setContentType("application/json");
        rgpaResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Unauthorized");
        errorResponse.put("message", "Unauthorized");
        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = rgpaResponse.getOutputStream();
        mapper.writeValue(out, errorResponse);
        out.flush();
    }
}
