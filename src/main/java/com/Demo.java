package com;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Storage storage=new Storage();
        storage.setStorageSize(4500);
        List<String> list=new ArrayList<>();
        list.add("jpg");
        list.add("txt");
        storage.setFormatsSupported(list);
        storage.setStorageCountry("Germany");
        StorageDAO storageDAO=new StorageDAO();
        storageDAO.save(storage);
    }
}
