package com;

import org.springframework.beans.factory.annotation.Autowired;

public class Controller {

    @Autowired
    private  Service service;

    public  void put(Storage storage, File file) throws Exception {
            service.put(storage, file);
    }

    public  void delete(Storage storage, File file) throws Exception {
            service.delete(storage, file);
    }

    public  void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
            service.transferAll(storageFrom, storageTo);
    }

    public  void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
            service.transferFile(storageFrom, storageTo, id);
    }
}
