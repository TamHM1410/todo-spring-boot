package com.example.petprojectspringboot.config.security;

import com.example.petprojectspringboot.user.UserEntity;
import com.example.petprojectspringboot.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity= userRepository.findByUsername(username);

        if(userEntity==null){
            throw new UsernameNotFoundException(username);
        }

        return new User(userEntity.getUsername(),userEntity.getPassword(),new ArrayList<>());

    }
}
