package com.example.aniomi.bankingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Transictionss extends AppCompatActivity {

    ArrayList<deatails> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transictionss);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Transictions");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot users : dataSnapshot.getChildren()) {

                    deatails temp = new deatails();
                    temp = users.getValue(deatails.class);
                    if(temp.id.equals(MainActivity.currentuser.getAcno())) list.add(temp);

                }
                adapter=new audi_free_adapter(list,Transictionss.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView=(RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        adapter=new audi_free_adapter(list,Transictionss.this);
        recyclerView.setAdapter(adapter);
    }

}
