package com.rmr101.campus.service;

import com.rmr101.campus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class CampusUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        com.rmr101.campus.entity.User user = userRepository.findByCampusId(userName)
                .orElseThrow(() -> new BadCredentialsException("Username:" + userName + "not existed"));

        return new User(user.getCampusId(),
                user.getPassword(),
                user.isActive(),true,true,true,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}
