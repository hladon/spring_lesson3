package com;

public class Controller {
    private  static  Service service=new Service();
    public static void put(Storage storage, File file) throws Exception {
            service.put(storage, file);
    }

    public static void delete(Storage storage, File file) throws Exception {
            service.delete(storage, file);
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
            service.transferAll(storageFrom, storageTo);
    }

    public static void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
            service.transferFile(storageFrom, storageTo, id);
    }
}
