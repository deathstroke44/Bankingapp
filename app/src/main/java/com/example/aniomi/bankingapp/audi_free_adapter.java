package com.example.aniomi.bankingapp;

/**
 * Created by aniomi on 5/4/18.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

/**
 * Created by aniomi on 5/1/18.
 */

public class audi_free_adapter  extends RecyclerView.Adapter<audi_free_adapter.sViewHolder>{


    private List<deatails> list;
    public Context context;
    public audi_free_adapter(List<deatails> list,Context mc) {
        this.list = list;context=mc;
    }

    public class sViewHolder extends RecyclerView.ViewHolder
    {
        public TextView t1,t2,t3;

        public CardView cv;
        public sViewHolder(View itemView) {
            super(itemView);
            cv=(CardView) itemView.findViewById(R.id.cv);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
            t3=itemView.findViewById(R.id.t3);
        }


    }
    @Override
    public audi_free_adapter.sViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddview,parent,false);
        return new audi_free_adapter.sViewHolder(v);
    }
    @Override
    public void onBindViewHolder(audi_free_adapter.sViewHolder holder, int position) {
        final deatails tmp=list.get(position);
        holder.t1.setText("Date : "+tmp.getRdate());
        holder.t2.setText("Amount : "+tmp.getBalance());
        holder.t3.setText("Status : "+tmp.getStatus());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }



}
