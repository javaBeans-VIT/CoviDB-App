package com.example.coviddb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.PatientName.setText(user.getPatientName());
        holder.PatientID.setText(user.getPatientID());
        holder.HospitalName.setText(user.getHospitalName());
        holder.Address.setText(user.getAddress());
        holder.Description.setText(user.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView PatientName, PatientID, HospitalName ,Address ,Description;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            PatientName = itemView.findViewById(R.id.ReviewActivityPatientName);
            PatientID = itemView.findViewById(R.id.ReviewActivityPatientID);
            HospitalName = itemView.findViewById(R.id.ReviewActivityHospitalName);
            Address = itemView.findViewById(R.id.ReviewActivityHospitalAddress);
            Description = itemView.findViewById(R.id.ReviewActivityDescription);

        }
    }

}
