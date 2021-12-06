package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.AbstractModel;
import com.bootcamp.demo.repository.exception.RepositoryException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.CollectionReference;
import org.apache.commons.beanutils.PropertyUtils;

public abstract class FirebaseAbstractRepository<T extends AbstractModel> implements IRepository<T> {
    private final Class<T> className;

    @SuppressWarnings("unchecked")
    public FirebaseAbstractRepository() {
        this.className = ((Class<T>) (
                (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public abstract CollectionReference getCollection();


    public Set<T> findAll() {
        try {
            Iterable<QueryDocumentSnapshot> documents = getCollection()
                    .get()
                    .get()
                    .getDocuments();
            Set<T> result = new HashSet<>();
            for (QueryDocumentSnapshot doc : documents) {
                T object = doc.toObject(className);
                object.setId(doc.getId());
                result.add(object);
            }
            return result;
        } catch (ExecutionException | InterruptedException e) {
            throw new RepositoryException("Exception findAll" + e);
        }
    }


    public String save(T element) {
        if (null == element) throw new IllegalArgumentException();
        try {
            ApiFuture<WriteResult> collectionFuture = getCollection()
                    .document(element.getId())
                    .set(element);
            return collectionFuture.get().getUpdateTime().toString();
        } catch (ExecutionException | InterruptedException e) {
            throw new RepositoryException("Exception save" + e);
        }
    }


    public String remove(String id) {
        if (null == id) throw new IllegalArgumentException();
        try {
            ApiFuture<WriteResult> collectionFuture = getCollection()
                    .document(id)
                    .delete();
            return collectionFuture.get().getUpdateTime().toString();
        } catch (ExecutionException | InterruptedException e) {
            throw new RepositoryException("Exception remove" + e);
        }
    }


    public T findById(String id) {
        DocumentReference documentReference = getCollection().document(id);
        try {
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();

            T element;
            if (documentSnapshot.exists()) {
                element = documentSnapshot.toObject(className);
                return element;
            } else {
                return null;
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RepositoryException("Exception findbyId" + e);
        }
    }

}
