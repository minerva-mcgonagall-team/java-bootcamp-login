package com.bootcamp.demo.repository;


import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface IRepository <T>{
    Set<T> findAll();
    String save(T t) ;
    String remove(String t);
    T findById (String id) throws ExecutionException, InterruptedException;
}
