package com.upc.api_examen_parcial_202120632.iservices.services;

import com.upc.api_examen_parcial_202120632.dtos.response.JwtResponseDTO;
import com.upc.api_examen_parcial_202120632.entities.User;
import com.upc.api_examen_parcial_202120632.iservices.IUserService;
import com.upc.api_examen_parcial_202120632.repositories.UserRepository;
import com.upc.api_examen_parcial_202120632.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository rgpaUserRepository;
    @Autowired
    private JwtUserDetailsService rgpaUserDetailsService;
    @Autowired
    private JwtTokenUtil rgpaJwtTokenProvider;
    @Autowired
    private AuthenticationManager rgpaAuthenticationManager;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JwtResponseDTO login(String rgpaUsername, String rgpaPassword) {
        User user = rgpaUserRepository.findByRgpaUsername(rgpaUsername);
        if (user == null) {
            throw new IllegalArgumentException("The user " + rgpaUsername + " does not exist");
        }
        try {
            authenticate(rgpaUsername, rgpaPassword);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        final UserDetails rgpaUserDetails = rgpaUserDetailsService.loadUserByUsername(rgpaUsername);
        final String rgpaToken = rgpaJwtTokenProvider.generateToken(rgpaUserDetails);
        return new JwtResponseDTO(rgpaToken);
    }

    @Transactional(rollbackFor = Exception.class)
    public void authenticate(String rgpaUsername, String rgpaPassword) throws Exception {
        try {
            rgpaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(rgpaUsername, rgpaPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
