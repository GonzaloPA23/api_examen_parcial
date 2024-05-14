package com.upc.api_examen_parcial_202120632.controllers;

import com.upc.api_examen_parcial_202120632.dtos.request.LoginRequestDTO;
import com.upc.api_examen_parcial_202120632.dtos.response.JwtResponseDTO;
import com.upc.api_examen_parcial_202120632.iservices.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Auth API")
@RestController
@SecurityRequirements
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IUserService rgpaUserService;

    @Operation(summary = "Inicia sesión")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO rpgaRequest) {
        try {
            JwtResponseDTO jwtResponse = rgpaUserService.login(rpgaRequest.getRgpaUsername(), rpgaRequest.getRgpaPassword());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", jwtResponse.getRgpaJwttoken());
            return ResponseEntity.ok().headers(responseHeaders).body(jwtResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
