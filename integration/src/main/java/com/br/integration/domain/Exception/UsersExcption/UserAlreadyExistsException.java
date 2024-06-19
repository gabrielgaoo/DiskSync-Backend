package com.br.integration.domain.Exception.UsersExcption;

public class UserAlreadyExistsException  extends RuntimeException{
            public UserAlreadyExistsException(String message){
                super(message);
            }
}
