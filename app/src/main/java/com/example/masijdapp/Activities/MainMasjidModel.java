package com.example.masijdapp.Activities;

import java.util.List;

public class MainMasjidModel {
    private int status;

    private final String name;
    private final String address;

    private  List<masjid_listModel> masjid_list;




    public MainMasjidModel(String name, String address, List<masjid_listModel> masjid_list) {
        this.name = name;
        this.address = address;
        this.masjid_list = masjid_list;
    }

    public int getStatus() {
        return status;
    }

    public List<masjid_listModel> getMasjid_list() {
        return masjid_list;
    }





    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
