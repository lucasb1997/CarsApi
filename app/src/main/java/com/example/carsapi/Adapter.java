package com.example.carsapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private final OnCarClick click;
    private List<Cars> carsList;
    private Context context;


    public Adapter(List<Cars> dataBase, Context context, OnCarClick click)
    {
        carsList =dataBase;
        this.context=context;
        this.click=click;
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView modelCar;
        public TextView markCar;
        public View layout;


        public ViewHolder(View vu)
        {
            super(vu);
            layout = vu;
            modelCar = (TextView) vu.findViewById(R.id.cars_model);
            markCar = (TextView) vu.findViewById(R.id.cars_mark);
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.car_row,parent,false);
        ViewHolder vuH = new ViewHolder(vu);
        return vuH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Cars carCurrent = carsList.get(position);
        final String model=carCurrent.getModel();
        final String mark=carCurrent.getMake();

        holder.modelCar.setText(model);
        holder.markCar.setText(mark + ":");

        holder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(carCurrent);
            }
        }));
    }

    @Override
    public int getItemCount()
    {
        return carsList.size();
    }
}
