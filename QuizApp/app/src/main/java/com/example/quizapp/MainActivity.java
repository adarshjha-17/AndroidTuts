package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button trueBtn, wrongBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueBtn = findViewById(R.id.trueBtn);
        wrongBtn = findViewById(R.id.wrongBtn);

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.trueBtn){
                    Log.i("Button Tapped","True");
                }else if(v.getId() == R.id.wrongBtn){

                    Log.i("Button Tapped","Wrong");
                }
            }
        };

        trueBtn.setOnClickListener(myListener);
        wrongBtn.setOnClickListener(myListener);


    }
}
