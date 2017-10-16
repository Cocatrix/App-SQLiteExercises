package fr.dta.formtion.app_mysqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by thomas on 16/10/2017.
 */

public class UserDataSource {
    public UserDBHelper helper;
    private SQLiteDatabase db;

    public UserDataSource(Context context){
        helper = new UserDBHelper(context);
    }

    public SQLiteDatabase getDb(){
        if(db == null) open();
        return db;
    }

    public void open() throws SQLException{
        db=helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    //Factories

    public UtilisateurDAO newUtilisateurDAO() {
        return new UtilisateurDAO(this);
    }
}
