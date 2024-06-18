package com.br.userapi.userapi.controller;

import com.br.userapi.userapi.config.security.TokenService;
import com.br.userapi.userapi.domain.Exception.UsersExcption.UserAlreadyExistsException;
import com.br.userapi.userapi.domain.entites.AuthDTO;
import com.br.userapi.userapi.domain.entites.LoginResponseDTO;
import com.br.userapi.userapi.domain.entites.User;
import com.br.userapi.userapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private TokenService tokenService;
        @Autowired
        private UserService usersService;
        @PostMapping("/save")
        public ResponseEntity<?> save(@RequestBody User user){
               try{
                    usersService.create(user);
                    return  new ResponseEntity<>(HttpStatus.CREATED);
               }catch(UserAlreadyExistsException e){
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
               }
        }
        @PostMapping("/auth")
        public ResponseEntity<?> auth(@RequestBody AuthDTO authDTO){
              var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.email(),authDTO.password());
              var auth = authenticationManager.authenticate(usernamePassword);
              var token = tokenService.generateToken((User) auth.getPrincipal());
              return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        @GetMapping("/teste")
        public ResponseEntity<String> testeController(){
            return ResponseEntity.ok("teste -  verificação de rota de autenticação");
        }
}
