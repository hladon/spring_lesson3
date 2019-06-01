package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class Service {

    private  Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public  File put(Storage storage, File file) throws Exception {
        checkRestriction(storage, file);
        file.setStorage(storage);
        repository.update(file);
        return file;
    }

    public  void delete(Storage storage, File file) throws Exception {
            file.setStorage(null);
            repository.update(file);
            return;
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = (File) repository.findById(id);
        if (file==null) {
            throw new Exception("File " + file.getId() + " don`t exist in storage ");
        }
        checkRestriction(storageTo, file);
        file.setStorage(storageTo);
        repository.update(file);
    }

    public  void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        List<File> list = repository.getFilesByStorage(storageFrom);
        long size=0;
        for (File file : list) {
            checkRestriction(storageTo, file);
            size+=file.getSize();
        }
        if (repository.getFreeStorageSpace(storageTo)>size){
            repository.updateList(list);
            return;}
        throw new Exception("Files to big for storage "+storageTo.getId());
    }



    private void checkRestriction(Storage storage, File file) throws Exception {
        if (repository.getFreeStorageSpace(storage) < file.getSize()) {
            System.out.println("File to big for storage"+ storage.getId());
            throw new Exception("File " + file.getId() + " to big for storage " + storage.getId());
        }
        if (repository.getFreeStorageSpace(storage) < file.getSize()) {
            throw new Exception("Storage " + storage.getId() + "to small for file " + file.getId());
        }
        for (String format : storage.getFormatsSupported()) {
            if (format.equalsIgnoreCase(file.getFormat()))
                return;
        }
        throw new Exception("Storage " + storage.getId() + "don`t support format of file " + file.getId());
    }
}
