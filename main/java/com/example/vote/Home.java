package com.example.vote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class Home extends AppCompatActivity {
    private DatabaseHelper db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db1 = new DatabaseHelper(this);

        final RadioButton radioButtonOption1 = findViewById(R.id.radioButtonOption1);
        final RadioButton radioButtonOption2 = findViewById(R.id.radioButtonOption2);
        Button buttonVote = findViewById(R.id.buttonVote);

        buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedOption = null;
                if (radioButtonOption1.isChecked()) {
                    selectedOption = "option 1";
                } else if (radioButtonOption2.isChecked()) {
                    selectedOption = "option 2";
                }

                if (selectedOption != null) {
                    // Add vote to database
                    db1.addVote(selectedOption);
                    Intent i=new Intent(Home.this,result.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Home.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
