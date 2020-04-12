package edu.uga.cs.geographyquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.RadioGroup;

/**
 * This class is the Database helper, where the DB Tables are created and
 * upgraded.
 * */
public class GeographyQuizDBHelper extends SQLiteOpenHelper {

    //Constant values for easier code reading
    private static final String DB_NAME = "geographyquiz.db";
    //Current version of the DB (Increment to rebuild database)
    // -- DO NOT DECREMENT --
    private static final int DB_VERSION = 16;

    //Name of the Geography Quizzes Table
    public static final String TABLE_GEOGRAPHYQUIZZES = "geographyquizzes";
    //Name of the columns for the Geography Quizzes Table
    public static final String GEOGRAPHYQUIZZES_COLUMN_ID = "id";
    public static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION1 = "q1";
    public static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION2 = "q2";
    public static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION3 = "q3";
    public static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION4 = "q4";
    public static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION5 = "q5";
    public static final String GEOGRAPHYQUIZZES_COLUMN_QUESTION6 = "q6";
    public static final String GEOGRAPHYQUIZZES_COLUMN_COMPLETED = "completed";
    public static final String GEOGRAPHYQUIZZES_COLUMN_SCORE = "score";
    public static final String GEOGRAPHYQUIZZES_COLUMN_DATE = "date";

    //Name of the Questions Table
    public static final String TABLE_GEOGRAPHYQUESTIONS = "geographyquestions";
    //Name of the columns for the Geography Quizzes Table
    public static final String GEOGRAPHYQUESTIONS_COLUMN_ID = "id";
    public static final String GEOGRAPHYQUESTIONS_COLUMN_CONTINENT = "continent";
    public static final String GEOGRAPHYQUESTIONS_COLUMN_COUNTRY = "country";

    /*SQL Statements for Executing the Table-Creating Queries*/
    //Creating Geography Quizzes Table
    private static final String CREATE_GEOGRAPHYQUIZZES =
            "CREATE TABLE " + TABLE_GEOGRAPHYQUIZZES + "("
                    + GEOGRAPHYQUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION1 + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION2 + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION3 + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION4 + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION5 + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_QUESTION6 + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_COMPLETED + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_SCORE + " INTEGER,"
                    + GEOGRAPHYQUIZZES_COLUMN_DATE + " TEXT,"
                    + "FOREIGN KEY(q1) REFERENCES TABLE_GEOGRAPHYQUESTIONS(id),"
                    + "FOREIGN KEY(q2) REFERENCES TABLE_GEOGRAPHYQUESTIONS(id),"
                    + "FOREIGN KEY(q3) REFERENCES TABLE_GEOGRAPHYQUESTIONS(id),"
                    + "FOREIGN KEY(q4) REFERENCES TABLE_GEOGRAPHYQUESTIONS(id),"
                    + "FOREIGN KEY(q5) REFERENCES TABLE_GEOGRAPHYQUESTIONS(id),"
                    + "FOREIGN KEY(q6) REFERENCES TABLE_GEOGRAPHYQUESTIONS(id)"
                    + ")";

    //Creating Geography Questions Table
    private static final String CREATE_GEOGRAPHYQUESTIONS =
            "CREATE TABLE " + TABLE_GEOGRAPHYQUESTIONS + "("
                    + GEOGRAPHYQUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GEOGRAPHYQUESTIONS_COLUMN_CONTINENT + " TEXT,"
                    + GEOGRAPHYQUESTIONS_COLUMN_COUNTRY + " TEXT"
                    + ")";

    //Private reference to the single instance
    private static GeographyQuizDBHelper helperInstance;

    /**
     * Private Constructor
     * @param context The context to set the DB to*/
    private GeographyQuizDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Public instance access method
     * @param context The context to instantiate the {GeographyQuizDBHelper} with
     * */
    public static synchronized GeographyQuizDBHelper getInstance(Context context){
        //If DB has not been instantiated
        if (helperInstance == null){
            //Instantiate DB
            helperInstance = new GeographyQuizDBHelper(context);
        }
        //Return instance of the DB
        return helperInstance;
    }

    /**
     * On Creation of this DB Helper, create needed tables
     * @param db The database with which to execute SQL statements*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CUSTOM", "DBHelper onCreate called");
        db.execSQL(CREATE_GEOGRAPHYQUESTIONS);
        db.execSQL(CREATE_GEOGRAPHYQUIZZES);
    }

    /**
     * If the DB version has incremented, update the oldVersion with the newVersion
     * @param db The database with which to execute SQL statements
     * @param oldVersion The old version of the db
     * @param newVersion The new version of the db*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop tables and recreate them if the version has changed
        db.execSQL("drop table if exists " + TABLE_GEOGRAPHYQUIZZES);
        db.execSQL("drop table if exists " + TABLE_GEOGRAPHYQUESTIONS);

        onCreate(db);
    }




}
