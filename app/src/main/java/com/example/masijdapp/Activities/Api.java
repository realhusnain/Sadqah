package com.example.masijdapp.Activities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("getMasjidList")
    Call<MainMasjidModel> getMasjid_list();

}
