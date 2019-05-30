package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class Service {
    @Autowired
    @Qualifier("FileDAO")
    private static Repository repository;

    public static File put(Storage storage, File file) throws Exception {
        checkRestriction(storage, file);
        file.setStorage(storage);
        repository.update(file);
        return file;
    }

    public static void delete(Storage storage, File file) throws Exception {
        if (file.getStorage().equals(storage)) {
            file.setStorage(null);
            repository.update(file);
        }
        throw new Exception("File " + file.getId() + " has different storage!");
    }

    public static void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = (File) repository.findById(id);
        transfer(storageFrom, storageTo, file);
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        List<File> list = repository.getFilesByStorage(storageFrom);
        for (File file : list) {
            transfer(storageFrom, storageTo, file);
        }
    }

    private static void transfer(Storage storageFrom, Storage storageTo, File file) throws Exception {
        if (file == null || !file.getStorage().equals(storageFrom)) {
            throw new Exception("File " + file.getId() + " don`t exist in storage " + storageFrom.getId());
        }
        checkRestriction(storageTo, file);
        file.setStorage(storageTo);
        repository.update(file);
    }

    private static void checkRestriction(Storage storage, File file) throws Exception {
        if (repository.getFreeStorageSpace(storage) > file.getSize()) {
            throw new Exception("Storage " + storage.getId() + "to small for file " + file.getId());
        }
        for (String format : storage.getFormatsSupported()) {
            if (format.equalsIgnoreCase(file.getFormat()))
                return;
        }
        throw new Exception("Storage " + storage.getId() + "don`t support format of file " + file.getId());
    }
}
