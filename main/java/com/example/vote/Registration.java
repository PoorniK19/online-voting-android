package com.example.vote;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button bb1,view;
    TextView tv;
    SQLiteDatabase db;
    AlertDialog.Builder builder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=(EditText) findViewById(R.id.etun1);
        e2=(EditText) findViewById(R.id.etemail);
        e3=(EditText) findViewById(R.id.etpass1);
        e4=(EditText) findViewById(R.id.etconpass);
        bb1=(Button) findViewById(R.id.registerbutton);
        tv=(TextView)findViewById(R.id.tvaa);
        view = (Button) findViewById(R.id.view);
        builder = new AlertDialog.Builder(this);
        db = openOrCreateDatabase("voting", MODE_PRIVATE, null);
        db.execSQL("create table if not exists users(Username text,Email email,Password password)");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, login.class));
            }
        });

        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = e1.getText().toString();
                String email=e2.getText().toString();
                String password= e3.getText().toString();
                String confirm=e4.getText().toString();
                Database db=new Database (getApplicationContext(),"voting",null,1);
                if(username.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    if (password.compareTo(confirm) == 0)
                    {
                        if (isvalid(password))
                        {
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registration.this, login.class));
                        }
                        else
                            {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, letters,digits and special character", Toast.LENGTH_SHORT).show();
                            }
                    }
                    else
                        {
                        Toast.makeText(getApplicationContext(), "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v)
            {
                StringBuffer sb = new StringBuffer();
                Cursor c = db.rawQuery("select * from users", null);
                while (c.moveToNext()) {
                    sb.append("username:" + c.getString(0) + "\t\n");
                    sb.append("Password:" + c.getString(2) + "\t\n\n");
                }
                builder.setMessage(sb.toString());
                builder.show();

            }
        });
    }
    public static boolean isvalid(String password){
        int f1=0,f2=0,f3=0;
        if(password.length()<8)
        {
            return false;

        }else
            {
            for(int p=0;p<password.length();p++)
            {
                if(Character.isLetter(password.charAt(p)))
                {
                    f1=1;
                }
            }
            for(int r=0;r<password.length();r++){
                if(Character.isDigit(password.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0;s<password.length();s++){
                char c =password.charAt(s);
                if(c>=33 && c<=46 ||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }

}
