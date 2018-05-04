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
import java.util.HashMap;

public class TransferFund extends AppCompatActivity {

    HashMap<String,Users> mp=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_fund);
        /*if(mp.containsKey("Omi")){
        String p=mp.get("Omi").balance+"";
        final TextView dd=findViewById(R.id.dd);
        dd.setText(p);}*/

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

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    Users temp = new Users();
                    temp = users.getValue(Users.class);
                    if(!temp.acno.equals(MainActivity.currentuser.acno))
                    {
                        mp.put(temp.acno,temp);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final TextView t1,t2,t3;
        final Button t4;
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().equals("") || t2.getText().toString().equals("") || t3.getText().toString().equals(""))
                {
                    Toast.makeText(TransferFund.this, "You have to fill all fiwld", Toast.LENGTH_LONG).show();
                }
                else{
                int bal=Integer.parseInt(t1.getText().toString());
                String pa=t2.getText().toString();
                String rid=t3.getText().toString();
                if(!MainActivity.currentuser.pass.equals(pa))
                {
                    Toast.makeText(TransferFund.this, "Wrong Password", Toast.LENGTH_LONG).show();
                }
                else if(!mp.containsKey(rid))
                {
                    Toast.makeText(TransferFund.this, "Wrong Reciver A/C", Toast.LENGTH_LONG).show();
                }
                else if(bal>MainActivity.currentuser.balance)
                {
                    Toast.makeText(TransferFund.this, "Not Enogh Money", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Users racc=mp.get(rid);
                    racc.balance+=bal;
                    MainActivity.currentuser.balance-=bal;
                    mp.put(racc.acno,racc);
                    DatabaseReference dataus= FirebaseDatabase.getInstance().getReference().child("Users");
                    dataus.child(MainActivity.currentuser.keys).setValue(MainActivity.currentuser);
                    dataus.child(racc.keys).setValue(racc);
                    Toast.makeText(TransferFund.this, "Success", Toast.LENGTH_LONG).show();
                    DatabaseReference tt=FirebaseDatabase.getInstance().getReference().child("Transictions");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date();
                    Date date1 = new Date();
                    String d,t;
                    d=(String)dateFormat.format(date);
                    t=(String)dateFormat1.format(date1);
                    deatails temp=new deatails(MainActivity.currentuser.acno,"Tarnsfer to "+rid,d,bal);
                    tt.push().setValue(temp);
                    temp=new deatails(rid,"Tarnsfer from "+MainActivity.currentuser.acno,d,bal);
                    tt.push().setValue(temp);
                }}
            }
        });
    }
}
