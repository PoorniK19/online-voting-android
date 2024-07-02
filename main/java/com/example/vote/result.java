package com.example.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {
    private DatabaseHelper db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db1=new DatabaseHelper(this);
        Button buttonShowResults = findViewById(R.id.res);
        final TextView textViewResults = findViewById(R.id.t1);

        buttonShowResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch vote counts from the database
                int votesForOption1 = db1.getVoteCount("option 1");
                int votesForOption2 = db1.getVoteCount("option 2");

                // Display the results
                textViewResults.setText("Results:\n\tAbigail : " + votesForOption1 + "\nMary Jane : " + votesForOption2);


            }

        });



    }
}

