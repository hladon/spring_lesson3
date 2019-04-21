package com;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;


@Controller
public class ItemController  {
    @Autowired
    private ItemService itemService=new ItemService();

    @RequestMapping(method = RequestMethod.GET,value = "/", produces = "text/plain")
    public @ResponseBody String doGet(@RequestParam(name = "id") String id ) {
        return itemService.read(id);
    }





    @DeleteMapping( value = "/",produces = "text/plain" )
    public @ResponseBody String doPost(@RequestParam(name = "id") String id) {
        itemService.delete(id);
        return "Delete is done";
    }




}
