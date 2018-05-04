package com.example.aniomi.bankingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        final TextView t1,t2,t3;
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t1.setText("A/C No : "+MainActivity.currentuser.acno);
        t2.setText("User Name : "+MainActivity.currentuser.name);
        t3.setText("Balance : "+MainActivity.currentuser.balance);

        //Own Info Update

        DatabaseReference lolo= FirebaseDatabase.getInstance().getReference().child("Users").child(MainActivity.currentuser.keys).child("balance");
        lolo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int bal=dataSnapshot.getValue(Integer.class);
                MainActivity.updatebal(bal);
                t3.setText("Balance : "+MainActivity.currentuser.balance);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Own Info Update

    }
}
