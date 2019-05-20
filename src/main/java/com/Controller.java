package com;

public class Controller {
    public static void put(Storage storage, File file) throws Exception {
        if (storage != null || file != null) {
            Service.put(storage, file);
            return;
        }
        throw new Exception("One of entities not initialized! ");
    }

    public static void delete(Storage storage, File file) throws Exception {
        if (storage != null || file != null) {
            Service.delete(storage, file);
            return;
        }
        throw new Exception("One of entities not initialized!");
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        if (storageFrom != null || storageTo != null) {
            Service.transferAll(storageFrom, storageTo);
            return;
        }
        throw new Exception("One of entities not initialized! ");
    }

    public static void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        if (storageFrom != null || storageTo != null) {
            Service.transferFile(storageFrom, storageTo, id);
            return;
        }
        throw new Exception("One of entities not initialized! ");
    }
}
