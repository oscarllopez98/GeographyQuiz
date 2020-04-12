package edu.uga.cs.geographyquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Quiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Quiz extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private TextView textView;
    private String mParam1;
    private String mParam2;
    //Array of the answers selected by the user as they do the quiz
    private static String[] selectedAnswers = new String[]{"NULL","NULL","NULL","NULL","NULL","NULL"};

    public Quiz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Quiz.
     */
    // TODO: Rename and change types and number of parameters
    public static Quiz newInstance(String param1, String param2) {
        Quiz fragment = new Quiz();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_quiz, container, false);

        //Get and Set unique attributes for this page

        textView = view.findViewById(R.id.questionPosition);
        //Page position of the quiz
        final int position = getArguments().getInt("POSITION");
        textView.setText("Question: " + position);

        //Question being asked based on position
        final GeographyQuizData quizData = new GeographyQuizData(getActivity());
        final GeographyQuestionData questionData = new GeographyQuestionData(getActivity());

        //Get current quiz from database
        long quizID = getArguments().getLong("QUIZ_ID");
        final GeographyQuiz currentQuiz = quizData.retrieveById((int)quizID);

        Button submitButton = view.findViewById(R.id.submit);
        String country = "EMPTY";
        //Get the name of the country for the current question
        switch (position){
            case 1:
                country = questionData.retrieveById((int)currentQuiz.getQuestion_1()).getCountry();
                break;
            case 2:
                country = questionData.retrieveById((int)currentQuiz.getQuestion_2()).getCountry();
                break;
            case 3:
                country = questionData.retrieveById((int)currentQuiz.getQuestion_3()).getCountry();
                break;
            case 4:
                country = questionData.retrieveById((int)currentQuiz.getQuestion_4()).getCountry();
                break;
            case 5:
                country = questionData.retrieveById((int)currentQuiz.getQuestion_5()).getCountry();
                break;
            case 6:
                //If on last page, show submit button
                submitButton.setVisibility(View.VISIBLE);
                country = questionData.retrieveById((int)currentQuiz.getQuestion_6()).getCountry();
                break;
            default:
                break;
        }
        String question = "Within which continent below is " + country + " located?";
        textView = view.findViewById(R.id.questionAsked);
        textView.setText(question);

        //Populate answers
        //Get Random continents

        String[] allAnswersArray = quizData.addRandomContinents(questionData.retrieveById((int)currentQuiz.getQuestion_1()).getContinent());
        List<String> temp = Arrays.asList(allAnswersArray);
        final List<String> allAnswersList = new ArrayList<>();
        allAnswersList.addAll(temp);

        //Assign answers to radio button randomly and remove them from the list
        RadioButton radioButton1 = view.findViewById(R.id.answer_choice_1);
        RadioButton radioButton2 = view.findViewById(R.id.answer_choice_2);
        RadioButton radioButton3 = view.findViewById(R.id.answer_choice_3);
        RadioButton radioButton4 = view.findViewById(R.id.answer_choice_4);

        int randomIndex = (int)(Math.random() * allAnswersList.size());
        radioButton1.setText(allAnswersList.get(randomIndex));
        allAnswersList.remove(randomIndex);

        randomIndex = (int)(Math.random() * allAnswersList.size());
        radioButton2.setText(allAnswersList.get(randomIndex));
        allAnswersList.remove(randomIndex);

        randomIndex = (int)(Math.random() * allAnswersList.size());
        radioButton3.setText(allAnswersList.get(randomIndex));
        allAnswersList.remove(randomIndex);

        randomIndex = (int)(Math.random() * allAnswersList.size());
        radioButton4.setText(allAnswersList.get(randomIndex));
        allAnswersList.remove(randomIndex);

        RadioButton[] radioButtons = {radioButton1,radioButton2,radioButton3,radioButton4};
        //If the correct answer is not displayed, force it to be displayed into random position
        if (!correctAnswerExists(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                .getQuestion_1()).getContinent(),radioButton1,radioButton2,radioButton3,radioButton4) && position==1){
            //Random option
            int random = (int)(Math.random()*4);

            Log.d("ALTER","CHANGE 1");
            //Edit that radio button value
            radioButtons[random].setText(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                    .getQuestion_1()).getContinent());
        }
        if (!correctAnswerExists(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                .getQuestion_2()).getContinent(),radioButton1,radioButton2,radioButton3,radioButton4) && position==2){
            //Random option
            int random = (int)(Math.random()*4);

            Log.d("ALTER","CHANGE 2");

            //Edit that radio button value
            radioButtons[random].setText(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                    .getQuestion_2()).getContinent());
        }
        if (!correctAnswerExists(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                .getQuestion_3()).getContinent(),radioButton1,radioButton2,radioButton3,radioButton4) && position==3){
            //Random option
            int random = (int)(Math.random()*4);

            Log.d("ALTER","CHANGE 3");

            //Edit that radio button value
            radioButtons[random].setText(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                    .getQuestion_3()).getContinent());
        }
        if (!correctAnswerExists(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                .getQuestion_4()).getContinent(),radioButton1,radioButton2,radioButton3,radioButton4) && position==4){
            //Random option
            int random = (int)(Math.random()*4);

            Log.d("ALTER","CHANGE 4");

            //Edit that radio button value
            radioButtons[random].setText(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                    .getQuestion_4()).getContinent());
        }
        if (!correctAnswerExists(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                .getQuestion_5()).getContinent(),radioButton1,radioButton2,radioButton3,radioButton4) && position==5){
            //Random option
            int random = (int)(Math.random()*4);

            Log.d("ALTER","CHANGE 5");

            //Edit that radio button value
            radioButtons[random].setText(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                    .getQuestion_5()).getContinent());
        }
        if (!correctAnswerExists(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                .getQuestion_6()).getContinent(),radioButton1,radioButton2,radioButton3,radioButton4) && position==6){
            //Random option
            int random = (int)(Math.random()*4);

            Log.d("ALTER","CHANGE 6");

            //Edit that radio button value
            radioButtons[random].setText(questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                    .getQuestion_6()).getContinent());
        }



        final RadioGroup radioGroup = view.findViewById(R.id.answerChoices);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedAnswers[position-1] = ((RadioButton) view.findViewById(group.getCheckedRadioButtonId())).getText().toString();
            }
        });

        //If submit button is clicked
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gather answers from each question
                String answer = "NULL";
                if (((RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString() != null){
                    answer = ((RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                }
                //If submitted, record score, and send user to results page
                int score = 0;
                for (int i = 0; i < 6; i++){
                    Log.d("TESTER",selectedAnswers[i]);

                }
                //Check if answer 1 is right, if so, increment score
                if (questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                        .getQuestion_1()).getContinent().equalsIgnoreCase(selectedAnswers[0])){
                    score++;
                }
                if (questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                        .getQuestion_2()).getContinent().equalsIgnoreCase(selectedAnswers[1])){
                    score++;
                }
                if (questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                        .getQuestion_3()).getContinent().equalsIgnoreCase(selectedAnswers[2])){
                    score++;
                }
                if (questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                        .getQuestion_4()).getContinent().equalsIgnoreCase(selectedAnswers[3])){
                    score++;
                }
                if (questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                        .getQuestion_5()).getContinent().equalsIgnoreCase(selectedAnswers[4])){
                    score++;
                }
                if (questionData.retrieveById((int)quizData.retrieveById((int)currentQuiz.getId())
                        .getQuestion_6()).getContinent().equalsIgnoreCase(selectedAnswers[5])){
                    score++;
                }

                Log.d("tester","Score: "+score);
                currentQuiz.setScore(score);
                quizData.updateScore(score, (int) currentQuiz.getId());

                //Send user to results page
                startActivity(new Intent(getActivity(), ResultsActivity.class));
            }
        });

        return view;
    }


    private boolean correctAnswerExists(String continent, RadioButton rb1,RadioButton rb2,RadioButton rb3,RadioButton rb4){

        if (continent.equalsIgnoreCase(rb1.getText().toString())) return true;
        else if (continent.equalsIgnoreCase(rb2.getText().toString())) return true;
        else if (continent.equalsIgnoreCase(rb3.getText().toString())) return true;
        else if (continent.equalsIgnoreCase(rb4.getText().toString())) return true;

        return false;
    }

}
