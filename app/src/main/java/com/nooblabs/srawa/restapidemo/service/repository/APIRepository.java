package com.nooblabs.srawa.restapidemo.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.nooblabs.srawa.restapidemo.service.model.RequestAPI;
import com.nooblabs.srawa.restapidemo.service.model.ResponseAPI;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRepository {

    private APIService apiService;
    private final String username = "admin";
    private final String password = "1234";

    private APIRepository() {
        apiService = createAPIService();
    }

    private static APIRepository INSTANCE = null;

    public static APIRepository getRepository() {
        if (INSTANCE == null) {
            INSTANCE = new APIRepository();
        }
        return INSTANCE;
    }


    private APIService createAPIService() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic(username, password));
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        })
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(APIService.class);

    }

    public LiveData<ResponseAPI> submitRequestData(RequestAPI requestData) {
        final MutableLiveData<ResponseAPI> data = new MutableLiveData<>();
        apiService.postData(requestData).enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, retrofit2.Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    Log.d("xyz", "failure: " + response.message());
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Log.d("xyz", t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }


}
