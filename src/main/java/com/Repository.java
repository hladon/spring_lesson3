package com;


import java.util.List;

public interface Repository<T> {

    T save(T object);

    void delete(long id);

    T update(T object);

    T findById(long id);

    long getFreeStorageSpace(Storage storage);

    List<File> getFilesByStorage(Storage storage);

    void updateList(List<T> list);
}
