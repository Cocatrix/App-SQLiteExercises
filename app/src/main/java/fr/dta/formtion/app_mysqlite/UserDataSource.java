package fr.dta.formtion.app_mysqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by thomas on 16/10/2017.
 */

public class UserDataSource {
    public UserDBHelper helper;
    public SQLiteDatabase db;
    public static UserDataSource usrDataSource;

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


    public synchronized static UserDataSource getInstance(Context context){
        if(usrDataSource==null){
            usrDataSource = new UserDataSource(context);
            usrDataSource.open();
        }
        return usrDataSource;
    }

    //Factories

    public UtilisateurDAO newUtilisateurDAO() {
        return new UtilisateurDAO(this);
    }
}
