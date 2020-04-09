package edu.uga.cs.geographyquiz;

import java.time.LocalDate;

/** Domain Class
 *POJO Class (Plain Old Java Object)
 * Represents a single geography quiz*/
public class GeographyQuiz {

    //Private members
    private long id;
    private int question_1;
    private int question_2;
    private int question_3;
    private int question_4;
    private int question_5;
    private int question_6;
    private int completed;
    private int score;
    private LocalDate date;

    //Default Constructor
    public GeographyQuiz(){
        this.id = -1;
        this.question_1 = -1;
        this.question_2 = -1;
        this.question_3 = -1;
        this.question_4 = -1;
        this.question_5 = -1;
        this.question_6 = -1;
        this.completed = 0;
        this.score = -1;
        LocalDate date = LocalDate.now();
        this.date = date;
    }

    //Constructor w/ Parameters
    public GeographyQuiz(int q1, int q2, int q3,
                         int q4, int q5, int q6){
        this.id = -1;
        this.question_1 = q1;
        this.question_2 = q2;
        this.question_3 = q3;
        this.question_4 = q4;
        this.question_5 = q5;
        this.question_6 = q6;
        this.completed = completed;
        this.score = score;

        LocalDate date = LocalDate.now();
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getQuestion_1() {
        return this.question_1;
    }

    public long getQuestion_2() {
        return this.question_2;
    }

    public long getQuestion_3() {
        return this.question_3;
    }

    public long getQuestion_4() {
        return this.question_4;
    }

    public long getQuestion_5() {
        return question_5;
    }

    public long getQuestion_6() {
        return question_6;
    }

    public int getCompleted() {
        return completed;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date.toString();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestion_1(int question_1) {
        this.question_1 = question_1;
    }

    public void setQuestion_2(int question_2) {
        this.question_2 = question_2;
    }

    public void setQuestion_3(int question_3) {
        this.question_3 = question_3;
    }

    public void setQuestion_4(int question_4) {
        this.question_4 = question_4;
    }

    public void setQuestion_5(int question_5) {
        this.question_5 = question_5;
    }

    public void setQuestion_6(int question_6) {
        this.question_6 = question_6;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
