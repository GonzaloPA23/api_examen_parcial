package com.upc.api_examen_parcial_202120632.security;

import com.upc.api_examen_parcial_202120632.iservices.services.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//Clase 6
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService rgpaJwtUserDetailsService;
	@Autowired
	private JwtTokenUtil rgpaJwtTokenUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest rgpaRequest,
									HttpServletResponse rgpaResponse,
									FilterChain rgpaChain)
			throws ServletException, IOException {
		final String requestTokenHeader = rgpaRequest.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = rgpaJwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("No se puede encontrar el token JWT");
			} catch (ExpiredJwtException e) {
				System.out.println("Token JWT ha expirado");
			}
		} else {
			logger.warn("JWT Token no inicia con la palabra Bearer");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.rgpaJwtUserDetailsService.loadUserByUsername(username);

			if (rgpaJwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(rgpaRequest));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		rgpaChain.doFilter(rgpaRequest, rgpaResponse);
	}

}
