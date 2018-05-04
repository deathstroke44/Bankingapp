package com.example.aniomi.bankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SIgnin extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
    ArrayList<Users> arrayList=new ArrayList<>();
    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    Users temp = new Users();
                    temp = users.getValue(Users.class);
                    arrayList.add(temp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final EditText t1,t2;final TextView t4;
        final Button t3;
        t3=findViewById(R.id.signinbutton);
        t1=findViewById(R.id.userID);
        t2=findViewById(R.id.pass);
        t4=findViewById(R.id.signUpbutton);
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=false;
                String uid=t1.getText().toString(),p=t2.getText().toString();
                for(int i=0;i<arrayList.size();i++)
                {
                    if(arrayList.get(i).acno.equals(uid) && arrayList.get(i).pass.equals(p)) {
                        flag=true;
                        MainActivity.currentuser=arrayList.get(i);
                        Intent intent = new Intent(SIgnin.this,userhome.class);
                        startActivity(intent);
                        finish();
                        break;
                    }
                }
                if(!flag) Toast.makeText(SIgnin.this, "Wrong userid or password", Toast.LENGTH_LONG).show();
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SIgnin.this,SignUp.class);
                startActivity(intent);

            }
        });
    }

}
