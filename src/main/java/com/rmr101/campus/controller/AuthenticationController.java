package com.rmr101.campus.controller;

import com.rmr101.campus.dto.authentication.AuthenticationRequest;
import com.rmr101.campus.dto.authentication.AuthenticationResponse;
import com.rmr101.campus.jwt.JwtUtil;
import com.rmr101.campus.service.CampusUserDetailService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CampusUserDetailService campusUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        final UserDetails userDetails = campusUserDetailService.loadUserByUsername(request.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
