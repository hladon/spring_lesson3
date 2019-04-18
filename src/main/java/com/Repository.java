package com;


public interface Repository<T> {

    T save(T object);
    void delete(long id);
    T update(T object);
    T findById(long id);


}
