package com.br.userapi.userapi.domain.Exception.UsersExcption;

public class UserAlreadyExistsException  extends RuntimeException{
            public UserAlreadyExistsException(String message){
                super(message);
            }
}
