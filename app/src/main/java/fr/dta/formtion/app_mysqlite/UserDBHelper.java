package fr.dta.formtion.app_mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thomas on 16/10/2017.
 */

public class UserDBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "Users.db";
    public static final int DB_VERSION = 1;

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    public static String getQueryOnCreate(){
        return "CREATE TABLE User("
                +"COL_ID Integer PRIMARY KEY AUTOINCREMENT,"
                +"COL_FIRSTNAME String NOT NULL,"
                +"COL_LASTNAME String NOT NULL,"
                +"COL_AGE Integer NOT NULL,"
                +"COL_JOB String NOT NULL"
                +");";
    }

    public static String getQueryDrop(){
        return "DROP TABLE IF EXISTS "+DB_NAME+";\"";
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getQueryOnCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getQueryDrop());
        db.execSQL(getQueryOnCreate());
    }
}


