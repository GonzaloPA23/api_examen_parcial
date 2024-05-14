package com.upc.api_examen_parcial_202120632.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {
    @Autowired
    private ModelMapper rgpaModelMapper;

    public <T, U> T convertToDto(U entity, Class<T> dtoClass) {
        return rgpaModelMapper.map(entity, dtoClass);
    }

    public <T, U> U convertToEntity(T dto, Class<U> entityClass) {
        return rgpaModelMapper.map(dto, entityClass);
    }
}
