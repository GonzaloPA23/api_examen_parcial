package com.upc.api_examen_parcial_202120632.iservices;

import com.upc.api_examen_parcial_202120632.dtos.response.JwtResponseDTO;

public interface IUserService {
    JwtResponseDTO login(String rgpaUsername, String rgpaPassword);
}
