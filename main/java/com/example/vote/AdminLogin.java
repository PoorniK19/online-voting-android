package com.example.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLogin extends AppCompatActivity {
    EditText us,p;
    Button res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        us=(EditText)findViewById(R.id.adUS);
        p=(EditText)findViewById(R.id.pwd);
        res=(Button)findViewById(R.id.sub);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(us.getText().toString().equals("Admin")&& p.getText().toString().equals("Admin@123"))
                {
                    Intent i = new Intent(AdminLogin.this,Registration.class);
                    startActivity(i);
                }
            }
        });

    }
}
