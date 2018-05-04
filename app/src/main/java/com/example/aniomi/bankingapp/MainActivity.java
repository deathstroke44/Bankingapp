package com.example.aniomi.bankingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class mypair
{
    int a,b;
    String resid;
    mypair(){};

    public mypair(int a, int b, String resid) {
        this.a = a;
        this.b = b;
        this.resid = resid;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }
}
public class MainActivity extends AppCompatActivity {

    static Users currentuser;
    static void updatebal(int m)
    {
        currentuser.balance=m;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String email="saminyeaserlight@gmail.com";
        String password="deathstroke";
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Hash");
                DatabaseReference ds;
                //DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference().child("Hash");
                Intent intent = new Intent(MainActivity.this,SIgnin.class);
                startActivity(intent);
                finish();
                //ds=databaseUsers.push();
                //mypair temp=new mypair(4444,777,ds.getKey()+"");
                //ds.setValue(temp);
            }
        });

    }
}
