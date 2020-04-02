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

    //Private reference to the single instance
    private static GeographyQuizDBHelper helperInstance;


    //Private Constructor
    private GeographyQuizDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
