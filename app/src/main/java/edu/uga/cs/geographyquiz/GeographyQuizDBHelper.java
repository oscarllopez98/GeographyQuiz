package edu.uga.cs.geographyquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class is the Database helper, where the DB is instantiated here and
 * is retrieved from.
 * */
public class GeographyQuizDBHelper extends SQLiteOpenHelper {

    //Constant values for easier code reading
    private static final String DB_NAME = "geographyquiz.db";
    private static final int DB_VERSION = 1;

    //Name of the Geography Quizzes Table
    private static final String TABLE_GEOGRAPHYQUIZZES = "geographyquizzes";
    //Name of the columns for the Geography Quizzes Table
    private static final String GEOGRAPHYQUIZZES_COLUMN_ID = "id";
    private static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION1 = "q1";
    private static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION2 = "q2";
    private static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION3 = "q3";
    private static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION4 = "q4";
    private static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION5 = "q5";
    private static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION6 = "q6";
    private static final String GEOGRAPHYQUIZZES_COLUMN_SCORE = "score";
    private static final String GEOGRAPHYQUIZZES_COLUMN_DATE = "date";

    //Name of the Questions Table
    private static final String TABLE_GEOGRAPHYQUESTIONS = "geographyquestions";
    //Name of the columns for the Geography Quizzes Table
    private static final String GEOGRAPHYQUESTIONS_COLUMN_ID = "id";
    private static final String GEOGRAPHYQUESTIONS_COLUMN_CONTINENT = "continent";
    private static final String GEOGRAPHYQUESTIONS_COLUMN_COUNTRY = "country";

    /*SQL Statements for Executing the Table Creating Queries*/
    //Creating Geography Quizzes Table
    private static final String CREATE_GEOGRAPHYQUIZZES =
            "create table " + TABLE_GEOGRAPHYQUIZZES + "("
                    + GEOGRAPHYQUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION1 + " TEXT,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION2 + " TEXT,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION3 + " TEXT,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION4 + " TEXT,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION5 + " TEXT,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION6 + " TEXT,"
                    + GEOGRAPHYQUIZZES_COLUMN_SCORE + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_DATE + " TEXT"
                    + ")";

    //Creating Geography Questions Table
    private static final String CREATE_GEOGRAPHYQUESTIONS =
            "create table " + TABLE_GEOGRAPHYQUIZZES + "("
                    + GEOGRAPHYQUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GEOGRAPHYQUESTIONS_COLUMN_CONTINENT + " TEXT,"
                    + GEOGRAPHYQUESTIONS_COLUMN_COUNTRY + " TEXT,"
                    + ")";

    //Private reference to the single instance
    private static GeographyQuizDBHelper helperInstance;

    //Private Constructor
    /**
     * Private Constructor*/
    private GeographyQuizDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Public instance access method (How other classes access the database)
    /**
     * Public instance access method
     * */
    public static synchronized GeographyQuizDBHelper getInstance(Context context){
        //If DB has not been instantiated
        if (helperInstance == null){
            //Instantiate DB
            helperInstance = new GeographyQuizDBHelper(context.getApplicationContext());
        }
        //Return instance of the DB
        return helperInstance;
    }

    /**
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * If the DB version has changed, update the oldVersion with the newVersion*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
