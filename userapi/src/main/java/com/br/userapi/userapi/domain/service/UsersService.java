package com.br.userapi.userapi.domain.service;

import com.br.userapi.userapi.domain.Exception.UsersExcption.UserAlreadyExistsException;
import com.br.userapi.userapi.domain.entites.Users;
import com.br.userapi.userapi.domain.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public Users create(Users user){

        Optional<Users> usersOptional = this.usersRepository.findByEmail(user.getEmail());

        if(usersOptional.isPresent()){
            throw new UserAlreadyExistsException(user.getEmail()+ " User already exists ");
        }

        return usersRepository.save(user);
    }
}
