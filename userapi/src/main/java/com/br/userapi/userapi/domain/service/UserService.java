package com.br.userapi.userapi.domain.service;

import com.br.userapi.userapi.config.security.TokenService;
import com.br.userapi.userapi.domain.Exception.UsersExcption.UserAlreadyExistsException;
import com.br.userapi.userapi.domain.entites.AuthDTO;
import com.br.userapi.userapi.domain.entites.User;
import com.br.userapi.userapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository usersRepository;
    @Autowired
    private TokenService tokenService;
    UserDetails userDetails;
    private final PasswordEncoder passwordEncoder;

    public User create(User user){

        if( this.usersRepository.findByEmail(user.getEmail())!= null){
            throw new UserAlreadyExistsException(user.getEmail()+ " User already exists ");
        }

        user = user.toBuilder().password(this.passwordEncoder.encode(user.getPassword())).build();

        return usersRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         userDetails = usersRepository.findByEmail(email);
         return  userDetails;
    }
}
