package edu.uga.cs.geographyquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Populates Questions Table if the table was just dropped
        populateOnNeed();

        //Only close when you are SURE you are done working with the db
        GeographyQuestionData questionData = new GeographyQuestionData(getApplicationContext());
        questionData.open();
        GeographyQuizData quizData = new GeographyQuizData(getApplicationContext());
        quizData.open();

        //Get all the questions that were added to the database
        List<GeographyQuestion> questionList = questionData.retrieveGeographyQuestions();
        Log.d("CUSTOM_TEST_LENGTH","" + questionList.size());

        //Random number between 0 and last index of questionList
        for (int i = 0; i < 5; i++) {
            double randomNum = Math.random() * (questionList.size() - 1);
            GeographyQuestion question = questionList.get((int) (randomNum));

            Log.d("CUSTOM_TEST", "Continent: " + question.getContinent() + "\tCountry: " + question.getCountry());
        }

        /*Done with Quiz Question Testing*/
        /*Start Quiz Testing*/

        GeographyQuiz quiz = quizData.generateQuiz();
        Log.d("QUIZ_TEST","QUIZ 1 ID: " + quiz.getId());
        Log.d("QUIZ_TEST", "QUESTION 1: " + quiz.getQuestion_1() + "\t" + questionData.retrieveById((int)quiz.getQuestion_1()));
        Log.d("QUIZ_TEST", "QUESTION 2: " + quiz.getQuestion_2() + "\t" + questionData.retrieveById((int)quiz.getQuestion_2()));
        Log.d("QUIZ_TEST", "QUESTION 3: " + quiz.getQuestion_3() + "\t" + questionData.retrieveById((int)quiz.getQuestion_3()));
        Log.d("QUIZ_TEST", "QUESTION 4: " + quiz.getQuestion_4() + "\t" + questionData.retrieveById((int)quiz.getQuestion_4()));
        Log.d("QUIZ_TEST", "QUESTION 5: " + quiz.getQuestion_5() + "\t" + questionData.retrieveById((int)quiz.getQuestion_5()));
        Log.d("QUIZ_TEST", "QUESTION 6: " + quiz.getQuestion_6() + "\t" + questionData.retrieveById((int)quiz.getQuestion_6()));

        GeographyQuiz quiz2 = quizData.retrieveById((int)quiz.getId());
        Log.d("QUIZ_TEST","QUIZ 2 ID: " + quiz2.getId());
        Log.d("QUIZ_TEST", "QUESTION 1: " + quiz2.getQuestion_1() + "\t" + questionData.retrieveById((int)quiz2.getQuestion_1()));
        Log.d("QUIZ_TEST", "QUESTION 2: " + quiz2.getQuestion_2() + "\t" + questionData.retrieveById((int)quiz2.getQuestion_2()));
        Log.d("QUIZ_TEST", "QUESTION 3: " + quiz2.getQuestion_3() + "\t" + questionData.retrieveById((int)quiz2.getQuestion_3()));
        Log.d("QUIZ_TEST", "QUESTION 4: " + quiz2.getQuestion_4() + "\t" + questionData.retrieveById((int)quiz2.getQuestion_4()));
        Log.d("QUIZ_TEST", "QUESTION 5: " + quiz2.getQuestion_5() + "\t" + questionData.retrieveById((int)quiz2.getQuestion_5()));
        Log.d("QUIZ_TEST", "QUESTION 6: " + quiz2.getQuestion_6() + "\t" + questionData.retrieveById((int)quiz2.getQuestion_6()));


        //Sami
        StartQuiz();

    }

    //Sami
    private void StartQuiz(){
        Button startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
            }
        });
    }

    private boolean populateOnNeed(){
        GeographyQuestionData questionData = new GeographyQuestionData(getApplicationContext());
        questionData.open();
        List<GeographyQuestion> questionList = questionData.retrieveGeographyQuestions();
        //If there are no questions stored in the database, populate and return true
        if (questionList.size() == 0){
            //Reading from the CSV File provided to us on eLC (country_continent.csv)
            try {
                InputStream data = getResources().openRawResource(R.raw.country_continent);
                CSVReader reader = new CSVReader(new InputStreamReader(data));

                // nextLine[] is an array of values from the line
                String[] nextLine;
                //While there exists data to read, read data
                while( ( nextLine = reader.readNext() ) != null ) {
                    //Get name of country (Index 0) and continent (Index 1)
                    //REMEMBER: GeographyQuestion's constructor args are (Continent, Country)
                    GeographyQuestion question = new GeographyQuestion(nextLine[1],nextLine[0]);

                    //Store current question into the database
                    questionData.storeGeographyQuestion(question);
                }//while
            }catch (Exception e) {
                Log.e("CVSReading", e.toString() );
            }
            return true;
        }
        else{
            return false;
        }
    }
}