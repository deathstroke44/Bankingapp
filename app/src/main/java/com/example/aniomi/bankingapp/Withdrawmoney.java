package com.example.aniomi.bankingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdrawmoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawmoney);


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


        final EditText t1,t2;
        final Button t3;
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().equals("") || t2.getText().toString().equals(""))
                {
                    Toast.makeText(Withdrawmoney.this, "You have to fill all fiwld", Toast.LENGTH_LONG).show();
                }
                else{
                int amount=Integer.parseInt(t1.getText().toString());
                String pa=t2.getText().toString();
                if(!pa.equals(MainActivity.currentuser.getPass()))
                {
                    Toast.makeText(Withdrawmoney.this, "Wrong Password", Toast.LENGTH_LONG).show();
                }
                else if(amount>MainActivity.currentuser.balance)
                {
                    Toast.makeText(Withdrawmoney.this, "Not enough Money", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Withdrawmoney.this, "Successful", Toast.LENGTH_LONG).show();
                    MainActivity.currentuser.balance-=amount;
                    DatabaseReference dataus= FirebaseDatabase.getInstance().getReference().child("Users");
                    dataus.child(MainActivity.currentuser.keys).setValue(MainActivity.currentuser);
                    DatabaseReference tt=FirebaseDatabase.getInstance().getReference().child("Transictions");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date();
                    Date date1 = new Date();
                    String d,t;
                    d=(String)dateFormat.format(date);
                    t=(String)dateFormat1.format(date1);
                    deatails temp=new deatails(MainActivity.currentuser.acno,"Withdraw",d,amount);
                    tt.push().setValue(temp);
                }}
            }
        });
    }
}
