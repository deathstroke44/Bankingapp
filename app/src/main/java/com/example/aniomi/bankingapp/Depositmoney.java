package com.example.aniomi.bankingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Depositmoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositmoney);

        DatabaseReference lolo= FirebaseDatabase.getInstance().getReference().child("Users").child(MainActivity.currentuser.keys).child("balance");
        lolo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int bal=dataSnapshot.getValue(Integer.class);
                MainActivity.updatebal(bal);
                //t3.setText("Balance : "+MainActivity.currentuser.balance);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final TextView t1;
        final Button t2;
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().equals(""))
                {
                    Toast.makeText(Depositmoney.this, "You Have to enter Amount", Toast.LENGTH_LONG).show();
                }
                else
                {
                    int amount=Integer.parseInt(t1.getText().toString());
                    MainActivity.currentuser.balance+=amount;
                    DatabaseReference dataus= FirebaseDatabase.getInstance().getReference().child("Users");
                    dataus.child(MainActivity.currentuser.keys).setValue(MainActivity.currentuser);

                    Toast.makeText(Depositmoney.this, "Succuss", Toast.LENGTH_LONG).show();
                    DatabaseReference tt=FirebaseDatabase.getInstance().getReference().child("Transictions");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date();
                    Date date1 = new Date();
                    String d,t;
                    d=(String)dateFormat.format(date);
                    t=(String)dateFormat1.format(date1);
                    deatails temp=new deatails(MainActivity.currentuser.acno,"Deposit",d,amount);
                    tt.push().setValue(temp);
                }
            }
        });


    }
}
