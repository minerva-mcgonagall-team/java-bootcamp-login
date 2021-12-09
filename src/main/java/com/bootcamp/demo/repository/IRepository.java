package com.bootcamp.demo.repository;


import com.bootcamp.demo.model.AbstractModel;

import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface IRepository<T extends AbstractModel> {
    Set<T> findAll();

    String save(T element);

    String remove(String element);

    T findById(String id) throws ExecutionException, InterruptedException;
}
