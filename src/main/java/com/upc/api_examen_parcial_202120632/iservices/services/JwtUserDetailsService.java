package com.upc.api_examen_parcial_202120632.iservices.services;

import com.upc.api_examen_parcial_202120632.entities.User;
import com.upc.api_examen_parcial_202120632.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Clase 2
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository rpgaRepo;
    @Override
    public UserDetails loadUserByUsername(String rpgaUsername) throws UsernameNotFoundException {
        User rgpaUser = rpgaRepo.findByRgpaUsername(rpgaUsername);

        if(rgpaUser == null) {
            throw new UsernameNotFoundException(String.format("User not exists", rpgaUsername));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        rgpaUser.getRgpaRoles().forEach(rol -> {
           roles.add(new SimpleGrantedAuthority(rol.getRgpaName()));
        });

        UserDetails rgpaUd = new org.springframework.security.core.userdetails.User(rgpaUser.getRgpaUsername(), rgpaUser.getRgpaPassword(), rgpaUser.getRgpaEnabled(), true, true, true, roles);

        return rgpaUd;
    }
}