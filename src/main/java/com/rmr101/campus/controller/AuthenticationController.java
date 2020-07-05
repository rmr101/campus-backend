package com.rmr101.campus.controller;

import com.rmr101.campus.dto.authentication.AuthenticationRequest;
import com.rmr101.campus.dto.authentication.AuthenticationResponse;
import com.rmr101.campus.entity.User;
import com.rmr101.campus.jwt.JwtUtil;
import com.rmr101.campus.repository.UserRepository;
import com.rmr101.campus.service.CampusUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        final UserDetails userDetails = campusUserDetailService.loadUserByUsername(request.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        //todo: second call to database for the same record ,can be refactored
        User user = userRepository.findByCampusId(request.getUsername())
                .orElseThrow(() ->new BadCredentialsException("Username doesn't exist."));

        return new AuthenticationResponse(jwt,user.getRole(),user.getUuid());
    }
}
