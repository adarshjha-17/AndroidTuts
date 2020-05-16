package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    private int attemptQuestions;
    final String SCORE_KEY = "SCORE";
    final String QUESTION_INDEX_KEY = "QUESTIONINDEX";
    final String ATTEMPT_QUESTIONS_KEY = "ATTEMPTQUESTONS";

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


        if (savedInstanceState != null){

            score = savedInstanceState.getInt(SCORE_KEY);
            attemptQuestions = savedInstanceState.getInt(ATTEMPT_QUESTIONS_KEY);
            questionIndex = savedInstanceState.getInt(QUESTION_INDEX_KEY);
            quizStatsTextView.setText("Score : " + score + "/" + attemptQuestions);
        }
        else{

            score = 0;
            attemptQuestions = 0;
            questionIndex = 0;

        }

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

        attemptQuestions++;
        quizStatsTextView.setText("Score : " + score + "/" + attemptQuestions);
        questionIndex = (questionIndex + 1) % quizQuestions.length;

        if (questionIndex == 0){

            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your score is " + score + "/" + attemptQuestions);
            quizAlert.setPositiveButton("Finsh the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });

            quizAlert.show();
        }
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

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "Onstart method", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Onresume method", Toast.LENGTH_LONG).show();
    }


    // used to save critical data
    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this,"Onpause method",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "Onstop method", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Ondestroy method", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(SCORE_KEY,score);
        outState.putInt(QUESTION_INDEX_KEY,questionIndex);
        outState.putInt(ATTEMPT_QUESTIONS_KEY,attemptQuestions);
    }
}
