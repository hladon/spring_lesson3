package com;

import java.util.List;

public class Service {

    private static Repository<File> fileDAO =new FileDAO();

    public static File put(Storage storage, File file) throws Exception {
        checkRestriction(storage, file);
        file.setStorage(storage);
        fileDAO.update(file);
        return file;
    }

    public static void delete(Storage storage, File file) throws Exception {
            file.setStorage(null);
            fileDAO.update(file);
            return;
    }

    public static void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = (File) fileDAO.findById(id);
        if (file==null) {
            throw new Exception("File " + file.getId() + " don`t exist in storage ");
        }
        checkRestriction(storageTo, file);
        file.setStorage(storageTo);
        fileDAO.update(file);
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        List<File> list = fileDAO.getFilesByStorage(storageFrom);
        long size=0;
        for (File file : list) {
            checkRestriction(storageTo, file);
            size+=file.getSize();
        }
        if (fileDAO.getFreeStorageSpace(storageTo)>size){
            fileDAO.updateList(list);
            return;}
        throw new Exception("Files to big for storage "+storageTo.getId());
    }



    private static void checkRestriction(Storage storage, File file) throws Exception {
        if (fileDAO.getFreeStorageSpace(storage) < file.getSize()) {
            System.out.println("File to big for storage"+ storage.getId());
            throw new Exception("File " + file.getId() + " to big for storage " + storage.getId());
        }
        if (fileDAO.getFreeStorageSpace(storage) < file.getSize()) {
            throw new Exception("Storage " + storage.getId() + "to small for file " + file.getId());
        }
        for (String format : storage.getFormatsSupported()) {
            if (format.equalsIgnoreCase(file.getFormat()))
                return;
        }
        throw new Exception("Storage " + storage.getId() + "don`t support format of file " + file.getId());
    }
}
