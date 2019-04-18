package com;


public interface DAO {

    Object save(Object object);
    void delete(long id);
    Object update(Object object);
    void findById(long id);


}
