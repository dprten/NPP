package com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dprtenjaca.carservices.R;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarService;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarServiceCompany;

import java.util.List;


public class CarServiceAdapter extends RecyclerView.Adapter<CarServiceAdapter.MyViewHolder> {

    private List<CarService> carServiceList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView serviceName;
        public TextView servicePrice;

        public MyViewHolder(View view) {
            super(view);
            serviceName = (TextView) view.findViewById(R.id.textView_user_car_service_company);
            servicePrice = (TextView) view.findViewById(R.id.textView_user_car_service_price);
        }
    }

    public CarServiceAdapter(List<CarService> carServiceList) {
        this.carServiceList = carServiceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_car_service_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CarService carService = carServiceList.get(position);
        holder.serviceName.setText(carService.getDescription());
        holder.servicePrice.setText(String.valueOf(carService.getPrice()) + " kn");
    }

    @Override
    public int getItemCount() {
        return carServiceList.size();
    }

    public void removeItem(int position) {
        carServiceList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, carServiceList.size());
    }
}
