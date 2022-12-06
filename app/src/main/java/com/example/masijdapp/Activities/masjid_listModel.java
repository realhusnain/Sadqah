package com.example.masijdapp.Activities;

import java.util.List;

public class masjid_listModel {

    private final String name;
    private final String address;


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public masjid_listModel(String name,String address) {
        this.name = name;
        this.address=address;
      
    }

}
