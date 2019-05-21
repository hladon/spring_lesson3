package com;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        StorageDAO storageDAO=new StorageDAO();
        FileDAO fileDAO=new FileDAO();

        try {
//            Controller.put(storageDAO.findById(1),fileDAO.findById(1));
//            Controller.delete(storageDAO.findById(1),fileDAO.findById(1));
            Controller.transferFile(storageDAO.findById(1),storageDAO.findById(2),1);
        }catch (Exception e){
            e.printStackTrace();
        }




//        Storage storage=new Storage();
//        List<String> list=new ArrayList<>();
//        list.add(  "txt");
//        list.add("doc");
//        storage.setFormatsSupported(list);
//        storage.setStorageCountry("Germany");
//        storage.setStorageSize(4500);
//        storageDAO.save(storage);

//        File file1=new File();

//        System.out.println(storageDAO.findById(1));
//        System.out.println(fileDAO.findById(1));
        System.out.println(fileDAO.getFreeStorageSpace(storageDAO.findById(2)));


    }
}
