package com.bootcamp.demo.repository;

import java.util.Set;

public interface UtilRepository {
    public Set<String> getAllPaths();

    public void deleteCollectionPath(String collectionName);
}
