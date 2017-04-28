package com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dprtenjaca.carservices.R;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.User;

import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<User> userList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userFullName;

        public MyViewHolder(View view) {
            super(view);
            userFullName = (TextView) view.findViewById(R.id.textView_admin_user_full_name);
        }
    }

    public UsersAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userFullName.setText(user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void removeItem(int position) {
        userList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, userList.size());
    }
}
