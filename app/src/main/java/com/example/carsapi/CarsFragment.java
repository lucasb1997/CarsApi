package com.example.carsapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarsFragment extends Fragment {
    private View v;
    private RecyclerView recycler;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progress;
    public List<Cars> listCars;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_cars, container, false);
        recycler = v.findViewById(R.id.my_recycler_view);
        progress = v.findViewById(R.id.loader);
        sharedPreferences=getActivity().getSharedPreferences("cache", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("cache")){
            showLoader();
            String listB= sharedPreferences.getString("cache",null);
            Type listT=new TypeToken<List<Cars>>(){}.getType();
            List<Cars> listBeer=new Gson().fromJson(listB,listT);
            showList(listBeer);
            hideLoader();
        }
        else{
            showLoader();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://private-anon-58a70027da-carsapi1.apiary-mock.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            CarApi beerApi = retrofit.create(CarApi.class);

            Call<List<Cars>> call = beerApi.getListCars();
            call.enqueue(new Callback<List<Cars>>() {
                @Override
                public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                    listCars = response.body();
                    Gson gson = new Gson();
                    String listB = gson.toJson(listCars);

                    sharedPreferences
                            .edit()
                            .putString("cache", listB)
                            .apply();

                    showList(listCars);
                    hideLoader();
                }
                @Override
                public void onFailure(Call<List<Cars>> call, Throwable t) {
                    Log.d("Erreur", "API erreur");
                }
            });
        }

        return v;
    }

    public void showLoader(){
        progress.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        progress.setVisibility(View.GONE);
    }

    public void showList(List<Cars> listBeer) {
        recycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter(listBeer, getActivity().getApplicationContext(), new OnCarClick() {
            @Override
            public void onItemClick(Cars cars) {
                Toast.makeText(getActivity().getApplicationContext(),cars.getModel(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity().getApplicationContext(),CarsDetails.class);
                intent.putExtra("model",cars.getModel());
                intent.putExtra("mark",cars.getMake());
                intent.putExtra("image",cars.getImg_url());
                intent.putExtra("prix",cars.getPrice());
                intent.putExtra("annee",cars.getYear());
                intent.putExtra("puissance",cars.getHorsepower());
                CarsFragment.this.startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);
    }
}
