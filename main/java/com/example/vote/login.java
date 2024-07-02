package com.example.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText e1,e2;

    Button bb,ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText) findViewById(R.id.etun);
        e2=(EditText) findViewById(R.id.etpass);
        bb=(Button) findViewById(R.id.sign);
        ad=(Button) findViewById(R.id.admin);

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = e1.getText().toString();
                String password=e2.getText().toString();
                Database db = new Database(getApplicationContext(),"voting",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,Home.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid username and password", Toast.LENGTH_SHORT).show();
                    }
                    // small memory to store data like cookies
                    SharedPreferences sharedPreferences=getSharedPreferences("share_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",username);
                    // to save our data with key and value
                    editor.apply();

                }


            }
        });
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,AdminLogin.class));
            }
        });
    }
}
