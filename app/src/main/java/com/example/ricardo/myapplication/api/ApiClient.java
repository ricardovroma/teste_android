package com.example.ricardo.myapplication.api;

import android.content.Context;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ricardo on 17/08/16.
 */
public class ApiClient {
    public ApiService service;
    public static String BASE_URL = "http://ricardoroma.com/android_test/";
    public static String TAG = "ApiClient";

    public ApiClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(ApiService.class);
    }
    public interface ApiService {

        @GET("contas.php")//mock dos dados, api fake somente com o date=18-08-2016
        Call<ArrayList<ProfileWithdrawal>> getProfileWithdrawal(@Query("date") String date);
    }
}

