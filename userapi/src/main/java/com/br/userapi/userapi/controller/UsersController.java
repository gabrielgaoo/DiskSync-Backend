package com.br.userapi.userapi.controller;

import com.br.userapi.userapi.domain.Exception.UsersExcption.UserAlreadyExistsException;
import com.br.userapi.userapi.domain.entites.Users;
import com.br.userapi.userapi.domain.repository.UsersRepository;
import com.br.userapi.userapi.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

        @Autowired
        UsersService usersService;
        @PostMapping("/save")
        public ResponseEntity<?> save(@RequestBody Users user){
               try{
                return  new ResponseEntity<>(usersService.create(user),HttpStatus.CREATED);
               }catch(UserAlreadyExistsException e){
                       return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
               }
        }
}
