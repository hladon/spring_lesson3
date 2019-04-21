package com;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ItemService {
    @Autowired

    public  void create(Item item){
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());

    }
    public  String read(String param){
        return null;

    }
    public  void update(Item item){

    }
    public  void delete(String id){

    }
}
