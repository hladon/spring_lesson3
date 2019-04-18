package com;

public class Service {
    private static Repository storageDAO=new StorageDAO();
    private static Repository fileDAO=new FileDAO();

    public static void put (Storage storage, File file)throws Exception{
        checkRestriction(storage,file);
        storage.setStorageSize(storage.getStorageSize()-file.getSize());

    }

    private static void checkRestriction (Storage storage, File file) throws Exception{
        if (storage.getStorageSize()>file.getSize()){
            throw new  Exception("Storage "+ storage.getId()+"to small for file "+ file.getId());
        }
        for (String format:storage.getFormatsSupported()){
            if (format.equalsIgnoreCase(file.getFormat()))
                return;
        }
        throw new  Exception("Storage "+ storage.getId()+"don`t support format of file "+ file.getId());
    }
}
