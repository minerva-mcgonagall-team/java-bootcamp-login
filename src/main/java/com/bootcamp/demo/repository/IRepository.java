package com.bootcamp.demo.repository;


import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface IRepository <T>{
    Set<T> findAll();
    String save(T t) ;
    void remove(T t);
    T findById (String id) throws ExecutionException, InterruptedException;
}
