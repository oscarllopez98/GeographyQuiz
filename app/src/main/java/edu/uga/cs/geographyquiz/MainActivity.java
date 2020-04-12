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

        //Begin quiz for user
        Log.d("CUSTOM","Start Quiz");
        StartQuiz();
        ViewResults();
    }

    private void ViewResults(){
        Button viewButton = findViewById(R.id.button2);
        viewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ResultsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void StartQuiz(){
        Button startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Generate Quiz and Random answers
                //Only close when you are SURE you are done working with the db
                GeographyQuizData quizData = new GeographyQuizData(getApplicationContext());
                GeographyQuestionData questionData = new GeographyQuestionData(getApplicationContext());
                //Open db connection
                quizData.open();
                questionData.open();
                //Generate Geography Quiz with unique answers
                GeographyQuiz newGeoQuiz = quizData.generateQuiz();
                newGeoQuiz.setScore(0);

                //Question List
                List<GeographyQuestion> questionList = questionData.retrieveGeographyQuestions();

                String[] wrongAnswersArray = quizData.addRandomContinents(questionData.retrieveById((int)newGeoQuiz.getQuestion_1()).getContinent());
                //3 Other wrong answers
                String wrongAnswersString = "," +
                        wrongAnswersArray[0] + "," +
                        wrongAnswersArray[1] + "," +
                        wrongAnswersArray[2] + "," +
                        wrongAnswersArray[3];
                //Print out Quiz questions and quiz info
                Log.d("TEST_QUIZ","ID: "+newGeoQuiz.getId());
                Log.d("TEST_QUIZ","Q1: "+questionData.retrieveById((int)newGeoQuiz.getQuestion_1()).getCountry() + wrongAnswersString);

                wrongAnswersArray = quizData.addRandomContinents(questionData.retrieveById((int)newGeoQuiz.getQuestion_2()).getContinent());
                //3 Other wrong answers
                wrongAnswersString = "," +
                        wrongAnswersArray[0] + "," +
                        wrongAnswersArray[1] + "," +
                        wrongAnswersArray[2] + "," +
                        wrongAnswersArray[3];
                Log.d("TEST_QUIZ","Q2: "+questionData.retrieveById((int)newGeoQuiz.getQuestion_2()).getCountry() + wrongAnswersString);

                wrongAnswersArray = quizData.addRandomContinents(questionData.retrieveById((int)newGeoQuiz.getQuestion_3()).getContinent());
                //3 Other wrong answers
                wrongAnswersString = "," +
                        wrongAnswersArray[0] + "," +
                        wrongAnswersArray[1] + "," +
                        wrongAnswersArray[2] + "," +
                        wrongAnswersArray[3];
                Log.d("TEST_QUIZ","Q3: "+questionData.retrieveById((int)newGeoQuiz.getQuestion_3()).getCountry() + wrongAnswersString);

                wrongAnswersArray = quizData.addRandomContinents(questionData.retrieveById((int)newGeoQuiz.getQuestion_4()).getContinent());
                //3 Other wrong answers
                wrongAnswersString = "," +
                        wrongAnswersArray[0] + "," +
                        wrongAnswersArray[1] + "," +
                        wrongAnswersArray[2] + "," +
                        wrongAnswersArray[3];
                Log.d("TEST_QUIZ","Q4: "+questionData.retrieveById((int)newGeoQuiz.getQuestion_4()).getCountry() + wrongAnswersString);

                wrongAnswersArray = quizData.addRandomContinents(questionData.retrieveById((int)newGeoQuiz.getQuestion_5()).getContinent());
                //3 Other wrong answers
                wrongAnswersString = "," +
                        wrongAnswersArray[0] + "," +
                        wrongAnswersArray[1] + "," +
                        wrongAnswersArray[2] + "," +
                        wrongAnswersArray[3];
                Log.d("TEST_QUIZ","Q5: "+questionData.retrieveById((int)newGeoQuiz.getQuestion_5()).getCountry() + wrongAnswersString);

                wrongAnswersArray = quizData.addRandomContinents(questionData.retrieveById((int)newGeoQuiz.getQuestion_6()).getContinent());
                //3 Other wrong answers
                wrongAnswersString = "," +
                        wrongAnswersArray[0] + "," +
                        wrongAnswersArray[1] + "," +
                        wrongAnswersArray[2] + "," +
                        wrongAnswersArray[3];
                Log.d("TEST_QUIZ","Q6: "+questionData.retrieveById((int)newGeoQuiz.getQuestion_6()).getCountry() + wrongAnswersString);

                //Start activity to Quiz
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                //Put ID of quiz into the extra, so it accessible from QuizActivity class
                intent.putExtra("QUIZ_ID",newGeoQuiz.getId());
                startActivity(intent);
            }
        });
    }

    /**
     * Method that populates the quiz question database, depending on if it needs to be repopulated.
     * Repopulates when the db version is incremented.
     * @return true If database is repopulated*/
    private boolean populateOnNeed(){
        //Interact with database and open connection
        GeographyQuestionData questionData = new GeographyQuestionData(getApplicationContext());
        questionData.open();
        //List of all current questions
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
                //Error reading CVS
                Log.e("CVSReading", e.toString() );
            }
            return true;
        }
        //Else, there were no questions stored in the database, so return false
        else{
            return false;
        }
    }
}