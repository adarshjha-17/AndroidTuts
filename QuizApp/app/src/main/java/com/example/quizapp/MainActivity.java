package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button trueBtn, wrongBtn;
    TextView questionTextView;

//    This array is create for only use to structure and packaged data because we are not using database

    private QuizModel[] quizQuestions = new QuizModel[]{

            new QuizModel(R.string.q1,true), new QuizModel(R.string.q2, false), new QuizModel(R.string.q3, true)
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueBtn = findViewById(R.id.trueBtn);
        wrongBtn = findViewById(R.id.wrongBtn);
        questionTextView = findViewById(R.id.questionTextView);

//        Object created to access the data
        QuizModel q1 = quizQuestions[0];
        questionTextView.setText(q1.getmQuestion());

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"True Clicked",Toast.LENGTH_SHORT).show();
            }
        };

        trueBtn.setOnClickListener(myListener);

        wrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Wrong Clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
