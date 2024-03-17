package com.example.Project1.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(int id){
        super("Not found");
    }
}
