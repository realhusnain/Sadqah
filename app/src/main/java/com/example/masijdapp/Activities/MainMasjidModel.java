package com.example.masijdapp.Activities;

import java.util.List;

public class MainMasjidModel {
    private int status;

    private  List<masjid_listModel> masjid_list;




    public MainMasjidModel(List<masjid_listModel> masjid_list) {
        this.masjid_list = masjid_list;
    }

    public int getStatus() {
        return status;
    }

    public List<masjid_listModel> getMasjid_list() {
        return masjid_list;
    }

}
