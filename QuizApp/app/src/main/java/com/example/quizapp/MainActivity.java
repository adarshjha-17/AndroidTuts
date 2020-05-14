package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView quizStatsTextView;
    private ProgressBar quizPB;
    Button trueBtn, wrongBtn;
    TextView questionTextView;
    private int questionIndex;
    private  int questionQuiz;
    private int score;

//    This array is create for only use to structure and packaged data because we are not using database

    private QuizModel[] quizQuestions = new QuizModel[]{

            new QuizModel(R.string.q1,false), new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4,false), new QuizModel(R.string.q5,false), new QuizModel(R.string.q6, true),
            new QuizModel(R.string.q7,true), new QuizModel(R.string.q8,false), new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10, true)
    };

    final int USER_PROGRESS = (int) Math.ceil(100.0 / quizQuestions.length);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueBtn = findViewById(R.id.trueBtn);
        wrongBtn = findViewById(R.id.wrongBtn);
        questionTextView = findViewById(R.id.questionTextView);
        quizStatsTextView = findViewById(R.id.quizStatsTextView);
        quizPB = findViewById(R.id.quizPB);

//        Object created to access the data
        QuizModel q1 = quizQuestions[0];
        questionTextView.setText(q1.getmQuestion());

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);
                updateQuestion();
            }
        };

        trueBtn.setOnClickListener(myListener);

        wrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);
                updateQuestion();
            }
        });


    }

    private void updateQuestion(){

        quizStatsTextView.setText(String.valueOf(score));
        questionIndex = (questionIndex + 1) % quizQuestions.length;
        questionQuiz = quizQuestions[questionIndex].getmQuestion();
        questionTextView.setText(questionQuiz);
        quizPB.incrementProgressBy(USER_PROGRESS);

    }


    private void checkAnswer(boolean userGuess){

        boolean correctAnswer = quizQuestions[questionIndex].ismAnswer();

        if (correctAnswer == userGuess){

            score += 1;
            Toast.makeText(getApplicationContext(),R.string.correct_answer_toast,Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.wrong_answer_toast,Toast.LENGTH_SHORT).show();
        }
    }
}
