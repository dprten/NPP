package com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dprtenjaca.carservices.R;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarServiceCompany;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.User;

import java.util.List;


public class CarServiceCompanyAdapter extends RecyclerView.Adapter<CarServiceCompanyAdapter.MyViewHolder> {

    private List<CarServiceCompany> carServiceCompanyList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView companyName;

        public MyViewHolder(View view) {
            super(view);
            companyName = (TextView) view.findViewById(R.id.textView_admin_car_services_company);
        }
    }

    public CarServiceCompanyAdapter(List<CarServiceCompany> carServiceCompanyList) {
        this.carServiceCompanyList = carServiceCompanyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_service_company_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CarServiceCompany carServiceCompany = carServiceCompanyList.get(position);
        holder.companyName.setText(carServiceCompany.getName());
    }

    @Override
    public int getItemCount() {
        return carServiceCompanyList.size();
    }

    public void removeItem(int position) {
        carServiceCompanyList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, carServiceCompanyList.size());
    }
}
