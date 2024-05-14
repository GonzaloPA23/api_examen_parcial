package com.upc.api_examen_parcial_202120632.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    private String rgpaUsername;
    private String rgpaPassword;
}
