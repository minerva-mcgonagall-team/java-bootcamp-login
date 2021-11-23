package com.bootcamp.demo.repository.exception;

public class RepositoryException extends RuntimeException{
    public RepositoryException(String errorMessage){
        super(errorMessage);
    }
}
