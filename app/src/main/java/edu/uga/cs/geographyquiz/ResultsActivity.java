package edu.uga.cs.geographyquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Start database
        GeographyQuizData quizData = new GeographyQuizData(getApplicationContext());

        //Load data into list
        List<GeographyQuiz> quizList = quizData.retrieveGeographyQuizzes();

        //Get table view
        TableLayout tableLayout = findViewById(R.id.table);

        //Return home button
        Button homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        //Load rows - Dependent on how many quizzes have been started
        int currentIndex = 0;
        if (quizList.size() == 0){
                TableRow row = new TableRow(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 10, 20, 10);
                row.setLayoutParams(layoutParams);

                TextView dateTitle = new TextView(this);
                TextView scoreTitle = new TextView(this);
                dateTitle.setText("DATE");
                scoreTitle.setText("SCORE");
                dateTitle.setTypeface(null, Typeface.BOLD);
                scoreTitle.setTypeface(null, Typeface.BOLD);

                row.addView(dateTitle, 0);
                row.addView(scoreTitle, 1);
                tableLayout.addView(row, currentIndex);
                currentIndex++;
        }

        for (int i = 0; i < quizList.size(); i++){

            //Create textviews
            //If at first row, add headers
            if (currentIndex == 0) {
                TableRow row = new TableRow(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 10, 20, 10);
                row.setLayoutParams(layoutParams);

                TextView dateTitle = new TextView(this);
                TextView scoreTitle = new TextView(this);
                dateTitle.setText("DATE");
                scoreTitle.setText("SCORE");
                dateTitle.setTypeface(null, Typeface.BOLD);
                scoreTitle.setTypeface(null, Typeface.BOLD);

                row.addView(dateTitle, 0);
                row.addView(scoreTitle, 1);
                tableLayout.addView(row, currentIndex);
                currentIndex++;
            }

            if (quizData.retrieveById((int)quizList.get(i).getId()).getScore() < 0) {
                Log.d("Score","Skip:"+quizData.retrieveById((int)quizList.get(i).getId()).getScore());
                continue;
            }
            else {

                Log.d("Score","Not Skip:"+quizData.retrieveById((int)quizList.get(i).getId()).getScore());


                TableRow row = new TableRow(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 10, 20, 10);
                row.setLayoutParams(layoutParams);

                //Date
                TextView date = new TextView(this);
                String dateText = quizData.retrieveById((int)quizList.get(i).getId()).getDate();
                date.setText(dateText);

                //Score
                TextView score = new TextView(this);
                String scoreText = "" + quizData.retrieveById((int)quizList.get(i).getId()).getScore();

                score.setText(scoreText);


                //Add views to the row
                row.addView(date, 0);
                row.addView(score, 1);


                //Add current row to bottom of the table
                tableLayout.addView(row, currentIndex);
                currentIndex++;
            }
        }


    }
}
