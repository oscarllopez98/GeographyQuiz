package edu.uga.cs.geographyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

public class QuizActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private QuizFragmentCollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        viewPager = findViewById(R.id.pager);

        //Get Quiz ID from intent extras
        long quizID = getIntent().getExtras().getLong("QUIZ_ID");
        Log.d("TEST_QUIZ","Quiz Id Found: "+quizID);

        adapter = new QuizFragmentCollectionAdapter(getSupportFragmentManager(), quizID);
        viewPager.setAdapter(adapter);
    }
}
