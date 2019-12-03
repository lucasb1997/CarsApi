package com.example.carsapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class CarsDetails extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_activity);

        getScdIntent();
    }

    private void getScdIntent(){
        if(getIntent().hasExtra("model")){
            String model = getIntent().getStringExtra("model");
            String price = getIntent().getStringExtra("prix");
            String mark = getIntent().getStringExtra("mark");
            String image = getIntent().getStringExtra("image");
            String annee = getIntent().getStringExtra("annee");
            String puissance = getIntent().getStringExtra("puissance");

            initTxt(model,price,mark,image,annee,puissance);



        }
    }

    private void initTxt(String model, String price, String mark, String image, String annee, String puissance){
        TextView modelTxtV=findViewById(R.id.car_model);
        modelTxtV.setText(model);

        TextView priceTxtV=findViewById(R.id.car_price);
        priceTxtV.setText(price + " $");

        ImageView pictureBeer= findViewById(R.id.car_picture);
        Picasso.with(getApplicationContext())
                .load(image)
                .resize(600, 300)
                .into(pictureBeer);

        TextView markTxtV=findViewById(R.id.car_mark);
        markTxtV.setText(mark);

        TextView anneeTxtV=findViewById(R.id.car_year);
        anneeTxtV.setText(annee);

        TextView puissanceTxtV=findViewById(R.id.car_power);
        puissanceTxtV.setText(puissance);
    }
}
