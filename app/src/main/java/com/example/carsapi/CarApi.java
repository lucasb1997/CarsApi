package com.example.carsapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarApi {
    @GET("cars")
    Call<List<Cars>> getListCars();
}
