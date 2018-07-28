package com.nooblabs.srawa.restapidemo.service.repository;

import com.nooblabs.srawa.restapidemo.service.model.RequestAPI;
import com.nooblabs.srawa.restapidemo.service.model.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    String BASE_URL = "http://api.shoocal.com/test/manager/";


    @POST("democalltesting")
    Call<ResponseAPI> postData(@Body RequestAPI requestData);

}
