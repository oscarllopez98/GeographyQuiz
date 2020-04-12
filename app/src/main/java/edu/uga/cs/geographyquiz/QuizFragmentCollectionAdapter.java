package edu.uga.cs.geographyquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class QuizFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    private long quizID = -1;

    public QuizFragmentCollectionAdapter(FragmentManager fm, long quizID) {
        super(fm);
        this.quizID = quizID;
    }

    @Override
    public Fragment getItem(int position) {
        Quiz quiz = new Quiz();
        Bundle bundle = new Bundle();
        position = position+1;
        bundle.putInt("POSITION", position);

        //Get current QuizID
        bundle.putLong("QUIZ_ID",getQuizID());
        quiz.setArguments(bundle);
        return quiz;
    }

    @Override
    public int getCount() {
        return 6; //This is where you set your page numbers
    }

    public long getQuizID(){
        return this.quizID;
    }
}
