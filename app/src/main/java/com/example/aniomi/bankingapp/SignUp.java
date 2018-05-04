package com.example.aniomi.bankingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    mypair mp[]=new mypair[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Hash");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    mypair temp = new mypair();
                    temp = users.getValue(mypair.class);
                    mp[0]=temp;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final EditText t1,t2,t3,t5;
        final Button t4;
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=mp[0].a;
                int b=mp[0].b;
                String name=t1.getText().toString();
                String vid=t2.getText().toString();
                String p=t5.getText().toString();
                int balance=Integer.parseInt(t3.getText().toString());
                int genid=10000000+a*1000+b;
                DatabaseReference dataus=FirebaseDatabase.getInstance().getReference().child("Users");
                DatabaseReference ds=dataus.push();
                Users temp=new Users(genid+"",p,vid,name,ds.getKey()+"",balance);
                ds.setValue(temp);
                mp[0].a++;
                mp[0].b++;
                dataus=FirebaseDatabase.getInstance().getReference().child("Hash");
                dataus.child(mp[0].resid).setValue(mp[0]);
            }
        });
    }
}
