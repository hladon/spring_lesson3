package com;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        StorageDAO storageDAO=new StorageDAO();
        Controller.put();



//        Storage storage=new Storage();
//        List<String> list=new ArrayList<>();
//        list.add(  "txt");
//        list.add("doc");
//        storage.setFormatsSupported(list);
//        storage.setStorageCountry("Germany");
//        storage.setStorageSize(4500);
//        storageDAO.save(storage);
        FileDAO fileDAO=new FileDAO();
//        File file1=new File();

//        System.out.println(storageDAO.findById(1));
//        System.out.println(fileDAO.findById(1));
        System.out.println(fileDAO.getFreeStorageSpace(storageDAO.findById(1)));


    }
}
